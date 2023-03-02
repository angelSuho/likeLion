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
                SayingEntity entity = new SayingEntity((long) famousSayings.size()+1, tmp1, tmp2);
                famousSayings.add(entity);
                System.out.println(famousSayings.size()  + "번 명언이 등록되었습니다.");
            }
            else if (Objects.equals(s, "목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("=============================");
                for (SayingEntity famousSaying : famousSayings) {
                    System.out.println(famousSaying.id + " / " + famousSaying.author + " / " + famousSaying.saying);
                }
            }
            else if (Objects.equals(s.substring(0,2), "삭제")) {
                for (SayingEntity famousSaying : famousSayings) {
                    if (famousSaying.getId() == Long.parseLong(tmpt[tmpt.length-1])) {
                        famousSayings.remove(Integer.parseInt(tmpt[tmpt.length-1])-1);
                        System.out.println(Integer.parseInt(tmpt[tmpt.length-1]) + "번 명언이 삭제되었습니다.");
                    }
                    else {
                        System.out.println(tmpt[tmpt.length - 1] + "번 명언은 존재하지 않습니다.");
                        break;
                    }
                }
            }
            else if (Objects.equals(s.substring(0,2), "수정")) {
                for (SayingEntity famousSaying : famousSayings) {
                    if (famousSaying.getId() == Long.parseLong(tmpt[tmpt.length-1])) {
                        System.out.println("명언(기존) : " + famousSaying.getSaying());
                        System.out.print("명언 : ");
                        String tmp1 = bf.readLine();

                        System.out.println("작자(기존) : " + famousSaying.getAuthor());
                        System.out.print("작자 : ");
                        String tmp2 = bf.readLine();

                        famousSaying.setSaying(tmp1);
                        famousSaying.setAuthor(tmp2);
                    }
                }
            }
            System.out.print("명령) ");
            s = bf.readLine();
        }
    }
}

