package com.sbnz.career.adviser.model;

public class TraitsResult {

	Long userId;
	Integer extraverted;
	Integer introverted;
	Integer assertive;
	Integer turbulent;
	Integer thinking;
	Integer feeling;
	Integer realist;
	Integer visionary;
	Integer judging;
	Integer prospecting;
	
	public TraitsResult() {
		this.extraverted = 0;
		this.introverted = 0;
		this.assertive = 0;
		this.turbulent = 0;
		this.thinking = 0;
		this.feeling = 0;
		this.realist = 0;
		this.visionary = 0;
		this.judging = 0;
		this.prospecting = 0;
	}

	public TraitsResult(Long userId, Integer extraverted, Integer introverted, Integer assertive, Integer turbulent, Integer thinking, Integer feeling,
			Integer realist, Integer visionary, Integer judging, Integer prospecting) {
		super();
		this.userId = userId;
		this.extraverted = extraverted;
		this.introverted = introverted;
		this.assertive = assertive;
		this.turbulent = turbulent;
		this.thinking = thinking;
		this.feeling = feeling;
		this.realist = realist;
		this.visionary = visionary;
		this.judging = judging;
		this.prospecting = prospecting;
	}
	public void incrementExtraverted() {
		this.extraverted++;
	}
	public void incrementIntroverted() {
		this.introverted++;
	}
	public void incrementAssertive() {
		this.assertive++;
	}
	public void incrementTurbulent() {
		this.turbulent++;
	}
	public void incrementThinking() {
		this.thinking++;
	}
	public void incrementFeeling() {
		this.feeling++;
	}
	public void incrementRealist() {
		this.realist++;
	}
	public void incrementVisionary() {
		this.visionary++;
	}
	public void incrementJudging() {
		this.judging++;
	}
	public void incrementProspecting() {
		this.prospecting++;
	}
	public void increment(String target) {
		switch(target) {
		case "extraverted":
			this.extraverted++;
			break;
		case "introverted":
			this.introverted++;
			break;
		case "assertive":
			this.assertive++;
			break;
		case "turbulent":
			this.turbulent++;
			break;
		case "thinking":
			this.thinking++;
			break;
		case "feeling":
			this.feeling++;
			break;
		case "realist":
			this.realist++;
			break;
		case "visionary":
			this.visionary++;
			break;
		case "judging":
			this.judging++;
			break;
		case "prospecting":
			this.prospecting++;
			break;
			
		}
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getExtraverted() {
		return extraverted;
	}

	public void setExtraverted(Integer extraverted) {
		this.extraverted = extraverted;
	}

	public Integer getIntroverted() {
		return introverted;
	}

	public void setIntraverted(Integer introverted) {
		this.introverted = introverted;
	}

	public Integer getThinking() {
		return thinking;
	}

	public void setThinking(Integer thinking) {
		this.thinking = thinking;
	}

	public Integer getFeeling() {
		return feeling;
	}

	public void setFeeling(Integer feeling) {
		this.feeling = feeling;
	}

	public Integer getRealist() {
		return realist;
	}

	public void setRealist(Integer realist) {
		this.realist = realist;
	}

	public Integer getVisionary() {
		return visionary;
	}

	public void setVisionary(Integer visionary) {
		this.visionary = visionary;
	}

	public Integer getJudging() {
		return judging;
	}

	public void setJudging(Integer judging) {
		this.judging = judging;
	}

	public Integer getProspecting() {
		return prospecting;
	}

	public void setProspecting(Integer prospecting) {
		this.prospecting = prospecting;
	}

	public Integer getAssertive() {
		return assertive;
	}

	public void setAssertive(Integer assertive) {
		this.assertive = assertive;
	}

	public Integer getTurbulent() {
		return turbulent;
	}

	public void setTurbulent(Integer turbulent) {
		this.turbulent = turbulent;
	}
	
	
	
	
}
