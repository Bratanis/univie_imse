import type { Tutorial } from './Tutorial';

export interface VideoTutorial extends Tutorial {
    length: number;
    url: string;
}
