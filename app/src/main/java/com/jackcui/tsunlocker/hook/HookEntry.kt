package com.jackcui.tsunlocker.hook

import com.highcapable.yukihookapi.annotation.xposed.InjectYukiHookWithXposed
import com.highcapable.yukihookapi.hook.factory.configs
import com.highcapable.yukihookapi.hook.factory.encase
import com.highcapable.yukihookapi.hook.log.loggerD
import com.highcapable.yukihookapi.hook.log.loggerE
import com.highcapable.yukihookapi.hook.xposed.proxy.IYukiHookXposedInit

@InjectYukiHookWithXposed
class HookEntry : IYukiHookXposedInit {

    override fun onInit() = configs {
        debugLog {
            tag = "TS_Hook"
        }
//        isDebug = false
    }

    override fun onHook() = encase {
        loadApp(name = "com.teamspeak.ts3client") {
//          V3.4.3
            findClass(name = "com.teamspeak.ts3client.s1").hook {
                injectMember {
                    method {
                        name = "b"
                    }.onNoSuchMethod {
                        loggerE(msg = "error:", e = it)
                    }
                    replaceUnit {
                        method {
                            name = "a"
                        }.onNoSuchMethod {
                            loggerE(msg = "error:", e = it)
                        }.get(instance).call(256)
                    }
                }.onHooked { member ->
                    loggerD(msg = "$member has hooked")
                }

                injectMember {
                    method {
                        name = "d"
                    }.onNoSuchMethod {
                        loggerE(msg = "error:", e = it)
                    }
                    replaceUnit {
                        method {
                            name = "a"
                        }.onNoSuchMethod {
                            loggerE(msg = "error:", e = it)
                        }.get(instance).call(256)
                    }
                }.onHooked { member ->
                    loggerD(msg = "$member has hooked")
                }
            }
            findClass(name = "com.teamspeak.ts3client.StartGUIFragment").hook {
                // 注入要 Hook 的方法
                injectMember {
                    method {
                        name = "B0"
                    }
                    replaceUnit {
                        method {
                            name = "A0"
                        }.get(instance).call()
                    }
                }
            }
//           V3.3.8
//            findClass(name = "d.g.f.e2").hook {
//                // 注入要 Hook 的方法
//                injectMember {
//                    method {
//                        name = "c"
//                    }.onNoSuchMethod {
//                        loggerE(msg = "error:", e = it)
//                    }
//                    replaceUnit {
//                        method {
//                            name = "a"
//                        }.onNoSuchMethod {
//                            loggerE(msg = "error:", e = it)
//                        }.get(instance).call(256)
//                    }
//                }.onHooked { member ->
//                    loggerD(msg = "$member has hooked")
//                }
//
//                injectMember {
//                    method {
//                        name = "b"
//                    }.onNoSuchMethod {
//                        loggerE(msg = "error:", e = it)
//                    }
//                    replaceUnit {
//                        method {
//                            name = "a"
//                        }.onNoSuchMethod {
//                            loggerE(msg = "error:", e = it)
//                        }.get(instance).call(256)
//                    }
//                }.onHooked { member ->
//                    loggerD(msg = "$member has hooked")
//                }
//            }
//            findClass(name = "com.teamspeak.ts3client.StartGUIFragment").hook {
//                // 注入要 Hook 的方法
//                injectMember {
//                    method {
//                        name = "z"
//                    }
//                    replaceUnit {
//                        method {
//                            name = "y"
//                        }.get(instance).call()
//                    }
//                }
//            }
//        }
        }
    }
}