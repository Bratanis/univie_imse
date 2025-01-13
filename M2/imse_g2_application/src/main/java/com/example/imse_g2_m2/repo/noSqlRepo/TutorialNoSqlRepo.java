package com.example.imse_g2_m2.repo.noSqlRepo;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.imse_g2_m2.model.Tutorial;
import com.example.imse_g2_m2.model.reports.BratanovReportDTO;
import com.example.imse_g2_m2.repo.reports.BratanovReportInterface;

@Repository("tutorialNoSqlRepo")
public interface TutorialNoSqlRepo extends MongoRepository<Tutorial, Integer>, BratanovReportInterface {

	// Using aggregation to simplify the complex query 
	@Override
    @Aggregation(pipeline = {
        "{ '$unwind': '$saved_tutorials' }",
        "{ '$lookup': { 'from': 'tutorials', 'localField': 'saved_tutorials', 'foreignField': 'tutorialId', 'as': 'tutorial_details' } }",
        "{ '$match': { 'tutorial_details.difficultyLevel': 'beginner', 'tutorial_details._class': 'VideoTutorial' } }",
        "{ '$group': { '_id': { 'tutorialId': '$saved_tutorials', 'name': { '$arrayElemAt': ['$tutorial_details.name', 0] }, 'url': { '$arrayElemAt': ['$tutorial_details.url', 0] } }, " +
                     "'timesSaved': { '$sum': 1 }, 'ages': { '$push': '$age' } } }",
        "{ '$addFields': { 'lowerAgeRange': { '$min': '$ages' }, 'upperAgeRange': { '$max': '$ages' } } }",
        "{ '$sort': { 'timesSaved': -1 } }",
        "{ '$limit': 1 }",
        "{ '$project': { '_id': 0, 'tutName': '$_id.name', 'tutUrl': '$_id.url', 'lowerAgeRange': 1, 'upperAgeRange': 1, 'timesSaved': 1 } }"
    })
	BratanovReportDTO getBeginnerTutorialReport();
}
