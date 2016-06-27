package com.databasecourse.salary.listener;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2016/2/19.
 */
public class MultipartListenerResolver extends CommonsMultipartResolver {

    @Override
    protected MultipartParsingResult parseRequest(HttpServletRequest request) throws MultipartException {
        String encoding = determineEncoding(request);
        FileUpload fileUpload = prepareFileUpload(encoding);

        fileUpload.setProgressListener(new FileUploadProgressListener(request.getSession()));

        try {
            List<FileItem>fileItems =((ServletFileUpload)fileUpload).parseRequest(request);
            return parseFileItems(fileItems,encoding);
        }catch (FileUploadBase.SizeLimitExceededException ex){
            throw new MaxUploadSizeExceededException(fileUpload.getSizeMax(), ex);
        } catch(FileUploadException e) {
            throw new MultipartException("Could not parse multipart servlet request", e);

        }
    }
}
