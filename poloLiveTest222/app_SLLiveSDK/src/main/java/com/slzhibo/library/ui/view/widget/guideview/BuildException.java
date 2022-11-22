package com.slzhibo.library.ui.view.widget.guideview;

public class BuildException extends RuntimeException {
    private static final long serialVersionUID = 6208777692136933357L;
    private final String mDetailMessage;

    public BuildException() {
        this.mDetailMessage = "General error.";
    }

    public BuildException(String str) {
        this.mDetailMessage = str;
    }

    public String getMessage() {
        return "Build GuideFragment failed: " + this.mDetailMessage;
    }
}
