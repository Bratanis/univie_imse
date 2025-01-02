import type { Location } from './Location';
import type { Tutorial } from './Tutorial';
import type { BankDetails } from './BankDetails';

export interface Member {
    memberId: number;
    name: string;
    age: number;
    //password: string; // Remove if not needed on the frontend
    bankDetails: BankDetails | null;
    location: Location | null;
    savedTutorials: Tutorial[];
}
