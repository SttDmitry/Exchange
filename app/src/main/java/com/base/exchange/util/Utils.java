package com.base.exchange.util;

import com.base.exchange.entity.ExchangeRate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class Utils {
    private static Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
        @Override
        public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return LocalDateTime.parse(json.getAsString()); }

    }).registerTypeAdapter(ZonedDateTime.class, new JsonDeserializer<ZonedDateTime>() {
        @Override
        public ZonedDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return ZonedDateTime.parse(json.getAsString()); }



    }).registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
        @Override
        public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.toString());
        }
    }).registerTypeAdapter(ZonedDateTime.class, new JsonSerializer<ZonedDateTime>() {
        @Override
        public JsonElement serialize(ZonedDateTime src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.toString());
        }
    })

            .create();

    public static Gson getGson(){
        return gson;
    }

    public static String arrayNormalize (String values) {
        StringBuilder sb = new StringBuilder("[");
        Arrays.stream(values.substring(5, values.length()-1).split(" ...=")).forEach(o -> {
            sb.append(o.substring(0, o.indexOf("Name=")));
            sb.append("Name=\"");
            String temp = o.substring(o.lastIndexOf("Name="));
            String subTemp = temp.substring(5, temp.indexOf(','));
            sb.append(subTemp).append("\"");
            sb.append(o.substring(o.lastIndexOf(subTemp)+subTemp.length()));
        });
        sb.append("]");
        return sb.toString();
    }

    public static String getExchange (ExchangeRate exchangeRate, Double roubles) {
        DecimalFormat df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.DOWN);
        double rate = exchangeRate.getValue() / exchangeRate.getNominal();
        return df.format(roubles / rate);
    }

    public static boolean zonedDateTimeDifference(ZonedDateTime d1, ChronoUnit unit){
        return unit.between(d1, ZonedDateTime.now()) > ChronoUnit.DAYS.getDuration().getSeconds();
    }
}
