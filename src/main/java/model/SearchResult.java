package model;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class SearchResult implements Serializable {
    private ArrayList<MemberInfo> result;

    public SearchResult(ArrayList<MemberInfo> result) {
        this.result = result;
    }

    public ArrayList<MemberInfo> getResult() {
        return result;
    }

    public void setResult(ArrayList<MemberInfo> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "result=" + result +
                '}';
    }
}
