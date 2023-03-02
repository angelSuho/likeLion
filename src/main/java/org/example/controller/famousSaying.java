package org.example.controller;

import org.example.service.fsService;
import org.example.entity.SayingEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class famousSaying {

    public static void main(String[] args) throws IOException {
        fsService fsService = new fsService();

        String fileName = fsService.jsonSearch();

        System.out.println("=== 명언 앱 ===");
        String s = fsService.answer();

        while (!Objects.equals(s, "종료")) {
            if (Objects.equals(s, "등록")) {
                fsService.add();
            }
            else if (Objects.equals(s, "목록")) {
                fsService.list();
            }
            else if (Objects.equals(s.substring(0,2), "삭제")) {
                fsService.delete(s);
            }
            else if (Objects.equals(s.substring(0,2), "수정")) {
                fsService.modify(s);
            }
            else if (Objects.equals(s.substring(0,2), "빌드")) {
                System.out.println(fileName + "파일의 내용이 갱신되었습니다.");
            }
            s = fsService.answer();
        }

        fsService.jsonSave();
    }
}

