package org.example.service;

import org.example.entity.SayingEntity;
import org.example.repository.fsRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

public class fsService {

    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    fsRepository fsRepository = new fsRepository();

    public String answer() throws IOException {
        System.out.print("명령) ");
        return bf.readLine();
    }

    public void add() throws IOException {

        System.out.print("명언 : ");
        String tmp1 = bf.readLine();
        System.out.print("작가 : ");
        String tmp2 = bf.readLine();

        SayingEntity entity = new SayingEntity(fsRepository.getFamousSayings().size()+1, tmp1, tmp2);
        fsRepository.add(entity);

        System.out.println(entity.getId() + "번 명언이 등록되었습니다.");
    }

    public void list() {

        System.out.println("번호 / 작가 / 명언");
        System.out.println("=============================");

        for (SayingEntity famousSaying : fsRepository.getFamousSayings()) {
            System.out.println(famousSaying.getId() + " / " + famousSaying.getAuthor() + " / " + famousSaying.getSaying());
        }
    }

    public void delete(String s) {

        String[] tempt = s.split("");

        for (SayingEntity famousSaying : fsRepository.getFamousSayings()) {
            if (famousSaying.getId() == Long.parseLong(tempt[tempt.length-1])) {
                fsRepository.delete(famousSaying.getId());
                System.out.println(famousSaying.getId() + "번 명언이 삭제되었습니다.");
                return;
            }
        }
        System.out.println(tempt[tempt.length - 1] + "번 명언은 존재하지 않습니다.");
    }

    public void modify(String s) throws IOException {

        String[] tempt = s.split("");

        for (SayingEntity famousSaying : fsRepository.getFamousSayings()) {

            if (famousSaying.getId() == Long.parseLong(tempt[tempt.length-1])) {

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

    public void jsonSave() throws IOException {

        JSONArray objects = new JSONArray();

        for (SayingEntity famousSaying : fsRepository.getFamousSayings()) {
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

    public String jsonSearch () {

        JSONParser parser = new JSONParser();
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
                            se.setId(Integer.parseInt(strings[1]));
                        } else if (strings[0].substring(1, strings[0].length() - 1).equals("saying")) {
                            se.setSaying(strings[1].substring(1,strings[1].length()-2));
                        } else {
                            try { se.setAuthor(strings[1].substring(1,strings[1].length()-1));}
                            catch (StringIndexOutOfBoundsException ignored) {}
                        }
                    }

                    list.add(se);
                }
                fsRepository.modifiedAll(list);
            } catch (IOException | ParseException e) { e.printStackTrace(); }
        }

        return f.getName();
    }
}
