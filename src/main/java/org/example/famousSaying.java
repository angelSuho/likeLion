package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class famousSaying {


    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<SayingEntity> famousSayings = new ArrayList<>();

        System.out.println("=== 명언 앱 ===");

        System.out.print("명령) ");
        String s = bf.readLine();

        while (!Objects.equals(s, "종료")) {
            String[] tmpt = s.split("");
            if (Objects.equals(s, "등록")) {
                System.out.print("명언 : ");
                String tmp1 = bf.readLine();
                System.out.print("작가 : ");
                String tmp2 = bf.readLine();
                SayingEntity entity = new SayingEntity(tmp1, tmp2);
                famousSayings.add(entity);
                System.out.println(famousSayings.size()  + "번 명언이 등록되었습니다.");
            }
            else if (Objects.equals(s, "목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("=============================");
                for (int i = 0; i < famousSayings.size(); i++) {
                    System.out.println((i+1) + " / " + famousSayings.get(i).author + " / " + famousSayings.get(i).saying);
                }
            }
            else if (Integer.parseInt(tmpt[tmpt.length-1]) > 0 && Integer.parseInt(tmpt[tmpt.length-1]) < 10) {
                famousSayings.remove(Integer.parseInt(tmpt[tmpt.length-1]));
                System.out.println(Integer.parseInt(tmpt[tmpt.length-1]) + "번 명언이 삭제되었습니다.");
            }
            System.out.print("명령) ");
            s = bf.readLine();
        }
    }
}

