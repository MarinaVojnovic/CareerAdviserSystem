export class TraitSelected{
    id: number;
    personalityField : string;
    target : string;

    constructor(id, personalityField, target){
        this.id=id;
        this.personalityField=personalityField;
        this.target=target;
    }
}