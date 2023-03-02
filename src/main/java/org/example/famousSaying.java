package org.example;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class famousSaying {


    public static void main(String[] args) throws IOException, ParseException {
        JSONArray objects = new JSONArray();
        JSONParser parser = new JSONParser();

        ArrayList<SayingEntity> famousSayings = new ArrayList<>();

        File f = new File("C:/Users/agsoo/OneDrive/바탕 화면/likeLion/src/main/java/org/example/arr.json");
        if (f.exists()) {
            ArrayList<SayingEntity> list = new ArrayList<>();
            try {
                FileReader reader = new FileReader("C:/Users/agsoo/OneDrive/바탕 화면/likeLion/src/main/java/org/example/arr.json");
                Object obj = parser.parse(reader);
                JSONArray jsonObject = (JSONArray) obj;

                for (Object o : jsonObject) {
                    String[] split = o.toString().split(",");
                    SayingEntity se = new SayingEntity();
                    for (String s : split) {
                        String[] strings = s.split(":");
                        if (strings[0].substring(1, strings[0].length() - 1).equals("id")) {
                            se.setId(Long.valueOf(strings[1]));
                        } else if (strings[0].substring(1, strings[0].length() - 1).equals("saying")) {
                            se.setSaying(strings[1].substring(1,strings[1].length()-2));
                        } else {
                            try {
                                se.setAuthor(strings[1].substring(1,strings[1].length()-1));
                            } catch (StringIndexOutOfBoundsException ignored) {
                            }
                        }
                    }
                    list.add(se);
                }
                famousSayings = list;
            } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

    }

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

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
                        break;
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
            else if (Objects.equals(s.substring(0,2), "빌드")) {
                System.out.println("arr.json 파일의 내용이 갱신되었습니다.");
            }
            System.out.print("명령) ");
            s = bf.readLine();
        }

        for (SayingEntity famousSaying : famousSayings) {
            JSONObject jo = new JSONObject();
            jo.put("id", famousSaying.getId());
            jo.put("saying", famousSaying.getSaying());
            jo.put("author", famousSaying.getAuthor());
            objects.add(jo);
        }
        FileWriter fileWriter = new FileWriter("C:/Users/agsoo/OneDrive/바탕 화면/likeLion/src/main/java/org/example/arr.json");
        fileWriter.write(objects.toJSONString());
        fileWriter.flush();
        fileWriter.close();
    }
}

