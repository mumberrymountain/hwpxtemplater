package kr.mumberrymountain.hwpxtemplater.linkedobj;

public interface LinkedObj <D, P, N, F> {
    N next();
    void setParent(P parent);
    P parent();
    D data();
    void setData(D data);

    void setDelimPos(F delimPos);

    F delimPos();
}
