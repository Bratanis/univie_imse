# University of Vienna; WS 2024; G2: Yordanov Georgi & Bratanov Ivan

## Why is the project structured the way it is:

- We have made the back-end model similar to the way the data will be displayed on the front-end, to simplify api calls and offload most of the complexity to the back-end. We have considerably less experience with front-end development than with back-end and databases and we wanted to keep the development simple.

## Business rules assumptions:

- In our scenario, only an admin can create new user accounts.
- Users login with their memberId (generated automatically and printed on their physical gym membership card) and their password
- We have left passwords unencrypted for the sake of simplicity, as it isnt't the main point of the project. Encrypting the passwords can easily be implemented in the future as it doesn't requite major restructuring of the code (thanks to spring security)

## Sources:
- The following project was used as a reference for the architecture and dockerization of the application: https://gitlab.dke.univie.ac.at/eis/2024s/group-2.git. It was developed by Dragomir Marinov, Klara Kolarova, Ivan Bratanov (also in the current project) for the EIS module of SS24 in the University of Vienna.
- The following tutorial explaining how to build an application using a front-end, a java back-end and database (including orms and spring boot): https://www.youtube.com/playlist?list=PLsyeobzWxl7qbKoSgR5ub6jolI8-ocxCF
