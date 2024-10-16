// Global variable to show login or sign up
var isLogin = true;

/**
 * Sign up button functionality
 */
function signup() {
  if (isLogin) {
    // Populate dynamic content
    isLogin = !isLogin;
    clearInputContainer();
    const inputContainer = document.getElementById('inputContainer');
    inputContainer.innerHTML = `
      <form class="signup">
        <p>First Name</p><input type="text"> 
        <p>Last Name</p><input type="text"> 
        <p>Username</p><input type="text">
        <p>Password</p><input type="password"> 
        <div>
            <button class="btn" onclick="login()">Login</button>
            <button class="btn" onclick="signup()">Sign Up</button>
        </div>
      </form>`;
  }
  else {
    // Communicate with Java
    console.log('Signing up...');
  }
}

/**
 * Login button functionality
 */
function login() {
  if (isLogin) {
    // Communicate with Java
    console.log('Logging in...');
  }
  else {
    // Populate dynamic content
    isLogin = !isLogin;
    clearInputContainer();
    const inputContainer = document.getElementById('inputContainer');
    inputContainer.innerHTML = `
      <form class="login">
        <p>Username</p><input type="text">
        <p>Password</p><input type="password"> 
        <div>
            <button class="btn" onclick="login()">Login</button>
            <button class="btn" onclick="signup()">Sign Up</button>
        </div>
      </form>`;
  }
}

/**
 * Clears the input container to show either the
 * login or sign up dynamic content
 */
function clearInputContainer() {
  const inputContainer = document.getElementById('inputContainer');
  inputContainer.innerHTML = '';
}