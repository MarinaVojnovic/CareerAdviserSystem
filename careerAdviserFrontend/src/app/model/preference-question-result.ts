import { User } from './user';
import { Preference } from './preference';

export class PreferenceQuestionResult{

    id : number;
    user : User;
    activity : Preference;
    isChecked : Boolean;
}

