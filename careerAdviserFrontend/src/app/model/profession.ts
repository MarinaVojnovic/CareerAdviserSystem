import { Preference } from './preference';
import { Trait } from './trait';
import { ProfessionalField } from './professional-field';

export class Profession {

    id : number;
    name: string;
    activities : Array<Preference>;
    traits : Array<Trait>;
    description : string;
    isActive : boolean;
    payment : number;
    employment : number;
    image : string;
}
