package com.kwameasamoa.finanaceapp;

public class SurveyData {
    private String questionnaire, questionnaire1, questionnaire2, questionnaire3, questionnaire4;

    public SurveyData(){

    }

    public SurveyData(String questionnaire, String questionnaire1, String questionnaire2, String questionnaire3, String questionnaire4) {
        this.questionnaire = questionnaire;
        this.questionnaire1 = questionnaire1;
        this.questionnaire2 = questionnaire2;
        this.questionnaire3 = questionnaire3;
        this.questionnaire4 = questionnaire4;

    }

    public String getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(String question) {
                this.questionnaire = questionnaire;
    }

    public String getQuestionnaire1() {
        return questionnaire1;
    }

    public void setQuestionnaire1(String question1) {
        this.questionnaire1 = questionnaire1;
    }

    public String getQuestionnaire2() {
        return questionnaire2;
    }

    public void setQuestionnaire2(String question2) {
        this.questionnaire2 = questionnaire2;
    }

    public String getQuestionnaire3() {
        return questionnaire3;
    }

    public void setQuestionnaire3(String question3) {
        this.questionnaire3 = questionnaire3;
    }
    public String getQuestionnaire4() {
        return questionnaire4;
    }

    public void setQuestionnaire4(String question4) {
        this.questionnaire4 = questionnaire4;
    }
}
