package pl.malak;

public enum Field {
    A(0),
    B(1),
    C(2),
    D(3),
    E(4),
    F(5),
    G(6),

    W_1(0),
    W_2(1),
    W_3(2),
    W_4(3),
    W_5(4),
    W_6(5),
    W_7(6),
    W_8(7),
    W_9(8),
    W_10(9),
    W_11(10),
    W_12(11),
    W_13(12),
    W_14(13),
    W_15(14),
    W_16(15),
    W_17(16),
    W_18(17),
    W_19(18),
    W_20(19),
    W_21(20),
    W_22(21),
    W_23(22),
    W_24(23),
    W_25(24),
    W_26(25),
    W_27(26),
    W_28(27),
    W_29(28),
    W_30(29),
    W_31(30),
    W_32(31),
    W_33(32),
    W_34(33),
    W_35(34),
    W_36(35);

    int value;

    private Field(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
