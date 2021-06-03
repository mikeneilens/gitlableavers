package config

val testResponse1 = """
    {
        "data": {
            "group": {
                "groupMembers": {
                    "pageInfo": {
                        "endCursor": "page0",
                        "hasNextPage": true
                    },
                    "nodes": [
                        {
                            "user": {
                                "id": "gid://gitlab/User/8576067",
                                "name": "name1",
                                "username": "F1.S1",
                                "publicEmail": "f1.s1@johnlewis.co.uk"
                            },
                            "accessLevel": {
                                "stringValue": "REPORTER"
                            }
                        },
                        {
                            "user": {
                                "id": "gid://gitlab/User/8015045",
                                "name": "name2",
                                "username": "F2.S2",
                                "publicEmail": "f2.s2@johnlewis.co.uk"
                            },
                            "accessLevel": {
                                "stringValue": "REPORTER"
                            }
                        }
                    ]
                }
            }
        }
    }
""".trimIndent()

val testResponse2 = """
    {
        "data": {
            "group": {
                "groupMembers": {
                    "pageInfo": {
                        "endCursor": "page1",
                        "hasNextPage": true
                    },
                    "nodes": [
                        {
                            "user": {
                                "id": "gid://gitlab/User/8576067",
                                "name": "name3",
                                "username": "F3.S3",
                                "publicEmail": "f3.s3@johnlewis.co.uk"
                            },
                            "accessLevel": {
                                "stringValue": "REPORTER"
                            }
                        },
                        {
                            "user": {
                                "id": "gid://gitlab/User/8015045",
                                "name": "name4",
                                "username": "F4.S4",
                                "publicEmail": "f4.s4@johnlewis.co.uk"
                            },
                            "accessLevel": {
                                "stringValue": "REPORTER"
                            }
                        }
                    ]
                }
            }
        }
    }
""".trimIndent()

val testResponse3 = """
    {
        "data": {
            "group": {
                "groupMembers": {
                    "pageInfo": {
                        "endCursor": "",
                        "hasNextPage": false
                    },
                    "nodes": [
                        {
                            "user": {
                                "id": "gid://gitlab/User/8576067",
                                "name": "name5",
                                "username": "F5.S5",
                                "publicEmail": "f5.s5@johnlewis.co.uk"
                            },
                            "accessLevel": {
                                "stringValue": "REPORTER"
                            }
                        },
                        {
                            "user": {
                                "id": "gid://gitlab/User/8015045",
                                "name": "name6",
                                "username": "F6.S6",
                                "publicEmail": "f6.s6@johnlewis.co.uk"
                            },
                            "accessLevel": {
                                "stringValue": "REPORTER"
                            }
                        }
                    ]
                }
            }
        }
    }
""".trimIndent()