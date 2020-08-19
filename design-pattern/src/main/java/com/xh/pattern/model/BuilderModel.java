package com.xh.pattern.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

public class BuilderModel {

    @Getter
    private String name;

    @Getter
    private int no;

    public static class InnerBuilder extends AbstractBuilder<BuilderModel>{

        @Setter
        @Accessors(chain = true)
        private String name;

        @Setter
        @Accessors(chain = true)
        private int no;

        @Override
        public BuilderModel build() {
            BuilderModel builderModel = new BuilderModel();
            builderModel.name = name;
            builderModel.no = no;
            return builderModel;
        }
    }
}
