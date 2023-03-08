package app;

public class App {
    public static void main(String[] args) throws Exception {
        NaturalPerson lucas = new NaturalPerson("Lucas", "Aureliano",
                "11919723803", "14150000",
                Gender.MASCULINO, 29, MaritalStatus.SOLTEIRO);

        System.out.println(lucas.toString());
    }
}

enum Gender {
    MASCULINO,
    FEMININO,
    OUTRO
}

enum MaritalStatus {
    SOLTEIRO,
    CASADO,
    DIVORCIADO
}

class NaturalPerson {
    private String firstName, lastName, cpf, postalCode;
    private Gender gender;
    private int age;
    private MaritalStatus maritalStatus;

    public NaturalPerson(String firstName, String lastName, String cpf, String postalCode, Gender gender, int age,
            MaritalStatus maritalStatus) {
        if (firstName == "" || lastName == "") {
            throw new IllegalArgumentException();
        }
        this.firstName = firstName;
        this.lastName = lastName;

        if (!isCPFValid(cpf)) {
            throw new IllegalArgumentException();
        }

        this.cpf = cpf;

        if (!postalCode.matches("[0-9]{8}")) {
            throw new IllegalArgumentException();
        }

        this.postalCode = postalCode;

        if (age <= 0 || age > 120) {
            throw new IllegalArgumentException();
        }

        this.age = age;

        this.gender = gender;
        this.maritalStatus = maritalStatus;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        if (!postalCode.matches("[0-9]{8}")) {
            throw new IllegalArgumentException();
        }

        this.postalCode = postalCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == "") {
            throw new IllegalArgumentException();
        }

        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == "") {
            throw new IllegalArgumentException();
        }

        this.lastName = lastName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (!isCPFValid(cpf)) {
            throw new IllegalArgumentException();
        }

        this.cpf = cpf;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age <= 0 || age > 120) {
            throw new IllegalArgumentException();
        }

        this.age = age;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
        result = prime * result + ((postalCode == null) ? 0 : postalCode.hashCode());
        result = prime * result + ((gender == null) ? 0 : gender.hashCode());
        result = prime * result + age;
        result = prime * result + ((maritalStatus == null) ? 0 : maritalStatus.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        NaturalPerson other = (NaturalPerson) obj;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (cpf == null) {
            if (other.cpf != null)
                return false;
        } else if (!cpf.equals(other.cpf))
            return false;
        if (postalCode == null) {
            if (other.postalCode != null)
                return false;
        } else if (!postalCode.equals(other.postalCode))
            return false;
        if (gender != other.gender)
            return false;
        if (age != other.age)
            return false;
        if (maritalStatus != other.maritalStatus)
            return false;
        return true;
    }

    private boolean isCPFValid(String cpf) {
        if (!cpf.matches("[0-9]{11}")) {
            return false;
        }

        int k = 10;
        int acc = 0;

        for (int i = 0; i < cpf.length() - 2; i++) {
            int n = Character.getNumericValue(cpf.charAt(i));
            acc += n * k;
            k -= 1;
        }

        int p = acc % 11;
        int firstDigit = p < 2 ? 0 : 11 - p;

        if (Character.getNumericValue(cpf.charAt(9)) != firstDigit) {
            System.out.println("Caiu nessa validação aqui");
            return false;
        }

        k = 11;
        acc = 0;
        for (int i = 0; i < cpf.length() - 1; i++) {
            int n = Character.getNumericValue(cpf.charAt(i));
            acc += n * k;
            k -= 1;
        }

        p = acc % 11;
        int secondDigit = p < 2 ? 0 : 11 - p;

        if (Character.getNumericValue(cpf.charAt(10)) != secondDigit) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "NaturalPerson [firstName=" + firstName + ", lastName=" + lastName + ", cpf=" + cpf + ", postalCode="
                + postalCode + ", gender=" + gender + ", age=" + age + ", maritalStatus=" + maritalStatus + "]";
    }
}