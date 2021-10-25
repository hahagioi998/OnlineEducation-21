package com.atguigu;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;

/**
 * @ClassName TestVod
 * @Description TODO
 * @Author lishan
 * @DATE 2021-10-20 12:29
 * @Version 1.0
 */
public class TestVod {
    /*获取播放凭证函数*/
    public static GetVideoPlayAuthResponse getVideoPlayAuth(DefaultAcsClient client) throws Exception {
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId("d3fe7a5831c94309aa7319f82738b19e");
        return client.getAcsResponse(request);
    }

    /*以下为调用示例*/
    public static void main(String[] argv) {
        DefaultAcsClient client;
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        try {
            client = InitObject.initVodClient("LTAI5tGvQ2mLqoEqJYz2zqe1", "hT17c2v6FDeHJatpj51r5MtFIjOY33");
            response = getVideoPlayAuth(client);
            //播放凭证
            System.out.print("PlayAuth = " + response.getPlayAuth() + "\n");
            //VideoMeta信息
            System.out.print("VideoMeta.Title = " + response.getVideoMeta().getTitle() + "\n");
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }
}
