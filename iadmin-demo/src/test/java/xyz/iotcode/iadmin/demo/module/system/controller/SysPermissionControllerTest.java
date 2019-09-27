package xyz.iotcode.iadmin.demo.module.system.controller;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SysPermissionControllerTest {

    public static void main(String[] args) {
        String body = HttpRequest.get("http://127.0.0.1:10010/v2/api-docs").execute().body();
        System.out.println(body);
        final int[] id = {10000};
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = JSONObject.parseObject(body);
        Map<String, Integer> map = new HashMap<>();
        JSONArray tags = jsonObject.getJSONArray("tags");
        for (int i = 0; i < tags.size(); i++) {
            JSONObject tag = (JSONObject) tags.get(i);
            JSONObject node = new JSONObject();
            node.put("id", id[0]++);
            node.put("url", "xx");
            node.put("permission_name", tag.getString("name"));
            node.put("type", 1);
            node.put("permission_code", UUID.randomUUID());
            node.put("pid", 0);
            jsonArray.add(node);
            map.put(node.getString("permission_name"), node.getInteger("id"));
        }
        JSONObject paths = jsonObject.getJSONObject("paths");
        paths.entrySet().stream().forEach(stringObjectEntry -> {
            JSONObject value = (JSONObject) stringObjectEntry.getValue();
            value.entrySet().forEach(stringObjectEntry1 -> {
                JSONObject post = (JSONObject) stringObjectEntry1.getValue();
                JSONArray tags1 = post.getJSONArray("tags");
                String summary = post.getString("summary");
                String operationId = post.getString("operationId");
                JSONObject node = new JSONObject();
                node.put("state", 1);
                node.put("request_way", stringObjectEntry1.getKey());
                node.put("id", id[0]++);
                node.put("url", stringObjectEntry.getKey());
                node.put("permission_name", summary);
                node.put("type", 2);
                node.put("permission_code", operationId);
                if (tags1!=null&&tags1.size()>0){
                    String s = (String) tags1.get(0);
                    Integer integer = map.get(s);
                    if (integer!=null){
                        node.put("pid", integer);
                    }else {
                        node.put("pid", 0);
                    }
                }else {
                    node.put("pid", 0);
                }
                jsonArray.add(node);
            });
        });
        System.out.println(jsonArray);
    }

}