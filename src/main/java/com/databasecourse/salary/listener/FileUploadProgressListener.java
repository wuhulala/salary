package com.databasecourse.salary.listener;

import org.apache.commons.fileupload.ProgressListener;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2016/2/19.
 */
public class FileUploadProgressListener implements ProgressListener {
    private HttpSession session;

    public FileUploadProgressListener() {
    }

    public FileUploadProgressListener(HttpSession session) {
        this.session = session;
    }

    public void update(long l, long l1, int i) {
        double progress = (double)l/l1;

        System.out.println("上传进度"+progress);

        session.setAttribute("progress",progress);

    }
}
