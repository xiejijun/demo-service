package com.wisenable.dms.constants;

public enum CategoryEm {
    FRAMES("镜架"),
    SUNGLASSES("太阳眼镜"),
    CUSTOMIZED("定制类眼镜"),
    READING_GLASSES("老花眼镜"),
    LENS("镜片"),
    CONTACT_LENSES("隐形眼镜"),
    ACCESSORY("配件"),
    SOLUTION("护理液"),
    OTHERS("其他");

    private String nameCn;

    CategoryEm(String name) {
        this.nameCn = name;
    }

    public String getNameCn() {
        return nameCn;
    }

    public static String getCategoryCnName(String category) {
        for (CategoryEm value : values()) {
            if (value.name().equals(category)) {
                return value.nameCn;
            }
        }
        return category;
    }

}
