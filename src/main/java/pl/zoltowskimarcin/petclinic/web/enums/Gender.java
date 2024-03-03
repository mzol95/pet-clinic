package pl.zoltowskimarcin.petclinic.web.enums;

public enum Gender {
    MALE, FEMALE;

    public static Gender valueOfLabel(int enumNumber) {
        for (Gender gender : values()) {
            if (gender.ordinal() == enumNumber) {
                return gender;
            }
        }
        return null;
    }
}
