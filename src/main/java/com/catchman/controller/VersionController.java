package com.catchman.controller;

import com.catchman.model.Constant;
import com.catchman.serviceImpl.IOhandler;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class VersionController {

    @RequestMapping(value = "/version", method = RequestMethod.GET)
    @ResponseBody
    public String getVersion() {
        return Constant.VERSION;
    }

    @RequestMapping(value = "/downloadsoftware", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> downtestversion(@RequestParam(value = "type", required = true) String type)
            throws IOException {

        String filePath = type.equals("1") ? Constant.TESTSOFTWARE : Constant.FORMATSOFTWARE;
        FileSystemResource file = new FileSystemResource(filePath);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new String(file.getFilename().getBytes("UTF-8"),"ISO-8859-1")));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(file.getInputStream()));
    }

    @RequestMapping(value = "/testrecord",method = RequestMethod.GET)
    @ResponseBody
    public Boolean user(@RequestParam(value = "mac", required = true) String mac) {
        return IOhandler.getTestRecord(mac);
    }
}
