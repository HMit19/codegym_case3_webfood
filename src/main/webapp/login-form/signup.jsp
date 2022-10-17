<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Sign Up</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="login-form/images/icons/favicon.ico"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="login-form/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="login-form/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="login-form/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="login-form/vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="login-form/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="login-form/css/util.css">
    <link rel="stylesheet" type="text/css" href="login-form/css/main.css">
    <!--===============================================================================================-->
</head>
<body>

<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">
            <div class="login100-pic js-tilt" data-tilt>
                <img src="login-form/images/img-01.png" alt="IMG">
            </div>

            <form class="login100-form validate-form" method="post" action="login-signup">
					<span class="login100-form-title">
						Member Sign up
					</span>
                <input type="hidden" name="action" value="signup">
                <input type="hidden" id="status" value="${status}">
                <div class="wrap-input100 validate-input" data-validate="Valid name is required: abc">
                    <input class="input100" type="text" name="username" placeholder="Username">
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
<%--                        icon username--%>
                            <i class="fa fa-user" aria-hidden="true"></i>
						</span>
                </div>
                <div class="wrap-input100 validate-input" data-validate="Valid email is required: ex@abc.xyz">
                    <input class="input100" type="text" name="email" placeholder="Email" value="">
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
							<i class="fa fa-envelope" aria-hidden="true"></i>
						</span>
                </div>
                <div class="wrap-input100 validate-input" data-validate="Valid email is required: 0xxxxxxxxxx">
                    <input class="input100" type="text" name="phone" placeholder="Phone">
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
                            <i class="fa fa-phone" aria-hidden="true"></i>
						</span>
                </div>

                <div class="wrap-input100 validate-input" data-validate="Password is required">
                    <input id="psw" class="input100" type="password" name="password" placeholder="Password" value="">
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
							<i class="fa fa-lock" aria-hidden="true"></i>
						</span>
                </div>
                <div class="wrap-input100 validate-input" data-validate="Password is required">
                    <input class="input100" type="password" name="re-password" placeholder="Confirm Password">
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
                            <i class="fa fa-lock" aria-hidden="true"></i>
                        </span>
                </div>
                <div class="wrap-input100 validate-input" data-validate="address not valid">
                    <input class="input100" type="text" name="address" placeholder="Address">
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
<%--                        icon biểu diễn cho location--%>
                            <i class="fa fa-map-marker" aria-hidden="true"></i>
                        </span>
                </div>

                <div class="container-login100-form-btn">
                    <input type="submit" value="Sign Up" class="login100-form-btn">
                </div>

                <div class="text-center p-t-70">
                    <a class="txt2" href="login-signup">
                        Sign in now!
                        <i class="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>
<!--===============================================================================================-->
<script src="login-form/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="login-form/vendor/bootstrap/js/popper.js"></script>
<script src="login-form/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="login-form/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
<script src="login-form/vendor/tilt/tilt.jquery.min.js"></script>
<script>
    $('.js-tilt').tilt({
        scale: 1.1
    })
</script>
<!--===============================================================================================-->
<script src="login-form/js/main.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script type="text/javascript">
    const status = document.getElementById("status").value;
    if (status === "false") {
        swal("Error!", "information not valid", "error");
    }if (status === "true") {
        swal("Success!", "Sign up successfully!", "success");
    }
</script>
</body>
</html>