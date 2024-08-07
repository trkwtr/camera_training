package com.yuruneji.cameratraining.data.remote

import com.yuruneji.cameratraining.domain.model.FaceAuthInfo

/**
 * @author toru
 * @version 1.0
 */
data class AppResponse(
    val result: Int,
    val img: String,
    val rect: String,
)

fun AppResponse.toConvert(): FaceAuthInfo {
    return FaceAuthInfo(
        result = result,
        name = img,
        rect = rect
    )
}
