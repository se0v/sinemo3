package com.example.sinemo

import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import org.telegram.passport.PassportScope
import org.telegram.passport.PassportScopeElementOne
import org.telegram.passport.PassportScopeElementOneOfSeveral
import org.telegram.passport.TelegramLoginButton
import org.telegram.passport.TelegramPassport
import java.util.*
val req = TelegramPassport.AuthRequest()
const val tgPassportResult = 352 // this can be any integer less than 0xFFFF
val payload: String = UUID.randomUUID().toString()
fun telepass(){

    req.botID = 443863171
    req.nonce = payload
    req.publicKey = "-----BEGIN PUBLIC KEY-----\n"+
            "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAzmgKr0fPP4rB/TsNEweC\n"+
            "hoG3ntUxuBTmHsFBW6CpABGdaTmKZSjAI/cTofhBgtRQIOdX0YRGHHHhwyLf49Wv\n"+
            "9l+XexbJOa0lTsJSNMj8Y/9sZbqUl5ur8ZOTM0sxbXC0XKexu1tM9YavH+Lbrobk\n"+
            "jt0+cmo/zEYZWNtLVihnR2IDv+7tSgiDoFWi/koAUdfJ1VMw+hReUaLg3vE9CmPK\n"+
            "tQiTy+NvmrYaBPb75I0Jz3Lrz1+mZSjLKO25iT84RIsxarBDd8iYh2avWkCmvtiR\n"+
            "Lcif8wLxi2QWC1rZoCA3Ip+Hg9J9vxHlzl6xT01WjUStMhfwrUW6QBpur7FJ+aKM\n"+
            "oaMoHieFNCG4qIkWVEHHSsUpLum4SYuEnyNH3tkjbrdldZanCvanGq+TZyX0buRt\n"+
            "4zk7FGcu8iulUkAP/o/WZM0HKinFN/vuzNVA8iqcO/BBhewhzpqmmTMnWmAO8WPP\n"+
            "DJMABRtXJnVuPh1CI5pValzomLJM4/YvnJGppzI1QiHHNA9JtxVmj2xf8jaXa1LJ\n"+
            "WUNJK+RvUWkRUxpWiKQQO9FAyTPLRtDQGN9eUeDR1U0jqRk/gNT8smHGN6I4H+NR\n"+
            "3X3/1lMfcm1dvk654ql8mxjCA54IpTPr/icUMc7cSzyIiQ7Tp9PZTl1gHh281ZWf\n"+
            "P7d2+fuJMlkjtM7oAwf+tI8CAwEAAQ==\n"+
            "-----END PUBLIC KEY-----"
    req.scope = PassportScope(
        PassportScopeElementOneOfSeveral(
        ).withSelfie(),
        PassportScopeElementOne(PassportScope.PHONE_NUMBER)
    )
}