package com.dutyChecker.slackbot.service;

import com.dutyChecker.slackbot.model.DutyInfo;
import com.dutyChecker.slackbot.model.DutyInfoTps;
import com.dutyChecker.slackbot.model.DutyReportData;
import com.dutyChecker.slackbot.repository.DutyInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Base64;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DutyInfoService {

    private final DutyInfoRepository dutyInfoRepository;

    // image 기능 추가
    public DutyReportData collectDutyReportData() {
        DutyInfo dutyInfo = dutyInfoRepository.findTop1ByOrderByLogDateDescLogTimeDesc()
                .stream().findFirst()
                .orElseThrow(() -> new RuntimeException("IF_WEB_CONNECTION 테이블에 데이터가 없습니다."));
        Double avgWebConCount = Optional.ofNullable(dutyInfoRepository.findAverageWebConCountOfRecent12())
                .orElse(0.0);
        DutyInfoTps tpsInfo = dutyInfoRepository.findTop1TPSByOrderByLogDateDescLogTimeDesc()
                .stream().findFirst()
                .orElseThrow(() -> new RuntimeException("tbTPSXView 테이블에 데이터가 없습니다."));
        return new DutyReportData(
                dutyInfo.getWebConCount(),
                avgWebConCount.intValue(),
                tpsInfo.getWasTps(),
                tpsInfo.getOffTps(),
                sendDataBase64(tpsInfo.getImage(), "image/png") // 이미지 URL
        );
    }

    /**
     * 16진수 문자열을 BASE64 인라인 이미지 형식으로 변환
     * @param hexString 16진수 문자열 (예: "0x89504E47...")
     * @param mimeType 이미지 MIME 타입 (예: "image/png")
     * @return BASE64 인라인 이미지 문자열 (예: "data:image/png;base64,...")
     */
    public String convertHexToInlineBase64(String hexString, String mimeType) {
        if (hexString.startsWith("0x") || hexString.startsWith("0X")) {
            hexString = hexString.substring(2);
        }

        byte[] bytes = new byte[hexString.length() / 2];
        for (int i = 0; i < hexString.length(); i += 2) {
            bytes[i / 2] = (byte) Integer.parseInt(hexString.substring(i, i + 2), 16);
        }

        String base64 = Base64.getEncoder().encodeToString(bytes);
        return "data:" + mimeType + ";base64," + base64;
    }

    /**
     * 문자열을 BASE64 인라인 이미지
     */
    public String sendDataBase64(String imagestr, String mimeType) {
        String base64 = imagestr;
        return "data:" + mimeType + ";base64," + base64;
    }


//    public DutyReportData collectDutyReportData() {
//        DutyInfo dutyInfo = dutyInfoRepository.findTop1ByOrderByLogDateDescLogTimeDesc()
//                .stream().findFirst()
//                .orElseThrow(() -> new RuntimeException("IF_WEB_CONNECTION 테이블에 데이터가 없습니다."));
//
//        Double avgWebConCount = Optional.ofNullable(dutyInfoRepository.findAverageWebConCountOfRecent12())
//                .orElse(0.0);
//
//        DutyInfoTps tpsInfo = dutyInfoRepository.findTop1TPSByOrderByLogDateDescLogTimeDesc()
//                .stream().findFirst()
//                .orElseThrow(() -> new RuntimeException("tbTPSXView 테이블에 데이터가 없습니다."));
//
//        return new DutyReportData(dutyInfo.getWebConCount(), avgWebConCount.intValue(), tpsInfo.getWasTps(), tpsInfo.getOffTps());
//    }
}