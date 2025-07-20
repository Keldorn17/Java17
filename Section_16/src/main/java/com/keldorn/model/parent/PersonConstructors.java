package main.java.com.keldorn.model.parent;

public record PersonConstructors(String name, String dob) {
//    public PersonConstructors(String name, String dob) {
//        this.name = name;
//        this.dob = dob.replace('-', '/');
//    }

    public PersonConstructors(PersonConstructors p) {
        this(p.name, p.dob);
    }

    public PersonConstructors {
        if (dob == null) throw  new IllegalArgumentException("Bad Data");
        dob = dob.replace('-', '/');
    }
}
