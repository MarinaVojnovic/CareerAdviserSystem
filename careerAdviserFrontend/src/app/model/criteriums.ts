export class Criteriums {
    personality : Boolean;
    preferences : Boolean;
    employment : Boolean;
    payment : Boolean;

    constructor(personality, preferences, employment, payment){
        this.personality=personality;
        this.preferences=preferences;
        this.employment=employment;
        this.payment=payment;
    }
}