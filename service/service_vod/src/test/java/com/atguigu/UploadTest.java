package com.atguigu;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;

import java.util.List;

/**
 * @ClassName UploadTest
 * @Description TODO
 * @Author lishan
 * @DATE 2021-10-20 12:07
 * @Version 1.0
 */
public class UploadTest {
    /*获取播放地址函数*/
    public static GetPlayInfoResponse getPlayInfo(DefaultAcsClient client) throws Exception {
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setVideoId("f6ddd2847c584f0f84ead0b1be3e9b71");
        return client.getAcsResponse(request);
    }

    /*以下为调用示例*/
    public static void main(String[] argv) {
        DefaultAcsClient client;
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        try {
            client = InitObject.initVodClient("LTAI5tGvQ2mLqoEqJYz2zqe1", "hT17c2v6FDeHJatpj51r5MtFIjOY33");
            response = getPlayInfo(client);
            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
            //播放地址
            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
                System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
            }
            //Base信息
            System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }

}
