package org.teamproject.lottocaptainteam.dto;

public class MemberSignupDTO {

    private final String inputId;
    private final String inputName;
    private final String inputPassword;
    private final String inputPasswordConfirm;
    private final String inputEmailAddress;

    private MemberSignupDTO(String inputId, String inputName, String inputPassword, String inputPasswordConfirm,
                            String inputEmailAddress) {
        this.inputId = inputId;
        this.inputName = inputName;
        this.inputPassword = inputPassword;
        this.inputPasswordConfirm = inputPasswordConfirm;
        this.inputEmailAddress = inputEmailAddress;
    }

    public static MemberSignupDTO create(String inputId,
                                         String inputName,
                                         String inputPassword,
                                         String inputPasswordConfirm,
                                         String inputEmailAddress) {
        return new MemberSignupDTO(inputId, inputName, inputPassword, inputPasswordConfirm, inputEmailAddress);
    }

    public MemberSignupDTO withId(String id) {
        return new MemberSignupDTO(id, this.inputName, this.inputPassword, this.inputPasswordConfirm, this.inputEmailAddress);
    }

    public MemberSignupDTO withName(String name) {
        return new MemberSignupDTO(this.inputId, name, this.inputPassword, this.inputPasswordConfirm, this.inputEmailAddress);
    }

    public MemberSignupDTO withPassword(String password) {
        return new MemberSignupDTO(this.inputId, this.inputName, password, this.inputPasswordConfirm, this.inputEmailAddress);
    }

    public MemberSignupDTO withPasswordConfirm(String passwordConfirm) {
        return new MemberSignupDTO(this.inputId, this.inputName, this.inputPassword, passwordConfirm, this.inputEmailAddress);
    }

    public MemberSignupDTO withEmail(String emailAddress) {
        return new MemberSignupDTO(this.inputId, this.inputName, this.inputPassword, this.inputPasswordConfirm, emailAddress);
    }

    public String getInputId() {
        return inputId;
    }

    public String getInputName() {
        return inputName;
    }

    public String getInputPassword() {
        return inputPassword;
    }

    public String getInputPasswordConfirm() {
        return inputPasswordConfirm;
    }

    public String getInputEmailAddress() {
        return inputEmailAddress;
    }
}
