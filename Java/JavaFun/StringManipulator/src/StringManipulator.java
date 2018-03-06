public class StringManipulator {
    public String trimAndConcat(String s1, String s2) {
        return s1.trim() + s2.trim();
    }

    public Integer getIndexOrNull(String s1, String s2) {
        int index = s1.indexOf(s2);
        if (index >= 0) {
            return index;
        } else {
            return null;
        }
    }

    public Integer getIndexOrNull(String s1, char c1) {
        int index = s1.indexOf(c1);
        if (index >= 0) {
            return index;
        } else {
            return null;
        }
    }

    public String concatSubstring(String s1, int start, int end, String s2) {
        return s1.substring(start,end)+s2;
    }
}
