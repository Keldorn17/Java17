package main.java.com.keldorn.model.sealed;

public sealed abstract class SpecialAbstractClass permits FinalKid, NonSealedKid, SealedKid, SpecialAbstractClass.Kid {

    static final class Kid extends SpecialAbstractClass {

    }
}
