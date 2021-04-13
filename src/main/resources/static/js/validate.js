
    $(document).ready(function() {
        $('#insert_modal').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                name: {
                    message: 'The username is not valid',
                    validators: {
                        notEmpty: {
                            message: '账号不能为空'
                        },
                        different: {
                            field: 'password',
                            message: '用户名和密码不能相同'
                        }
                    }
                },
                idcard:{

                    validators: {
                        notEmpty: {
                            message: '身份证号不能为空'
                        },
                        regexp: {
                            regexp:/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/,
                            message: '身份证号不符合格式'
                        }
                    }
                },
                kaptcha:{
                    validators: {
                        notEmpty: {
                            message: '验证码不能为空'
                        },
                    }
                },
                password: {
                    validators: {
                        notEmpty: {
                            message:'密码不能为空'
                        },
                        different: {
                            field: 'username',
                            message: '用户名和密码不能相同'
                        },
                        callback: {
                            callback: function(value, validator) {
                                // Check the password strength
                                if (value.length < 6) {
                                    return {
                                        valid: false,
                                        message: '密码必须多于6个字符'
                                    }
                                }

                                if (value === value.toLowerCase()) {
                                    return {
                                        valid: false,
                                        message: '密码至少含有一个大写字母'
                                    }
                                }
                                if (value === value.toUpperCase()) {
                                    return {
                                        valid: false,
                                        message: '密码至少含有一个小写字母'
                                    }
                                }
                                if (value.search(/[0-9]/) < 0) {
                                    return {
                                        valid: false,
                                        message: '密码至少含有一个数字'
                                    }
                                }

                                return true;
                            }
                        }
                    }
                },
                truename: {
                    message: 'The truename is not valid',
                    validators: {
                        notEmpty: {
                            message: '姓名不能为空'
                        },
                    }
                },
            }
        });
    });
