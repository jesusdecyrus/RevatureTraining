// Global variable to show login or sign up
var isLogin = true;

/**
 * Sign up button functionality
 */
function signup(event) {
  // Prevent default form submission
  event.preventDefault();
  
  // Sign up
  if (isLogin) {
    // Populate dynamic content
    isLogin = !isLogin;
    clearInputContainer();
    const inputContainer = document.getElementById('inputContainer');
    inputContainer.innerHTML = `
      <form class="signup">
        <p>First Name</p><input type="text"/> 
        <p>Last Name</p><input type="text"/> 
        <p>Username</p><input type="text"/>
        <p>Password</p><input type="password"/> 
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
function login(event) {
  // Prevent default form submission
  event.preventDefault();
  
  // Login
  if (isLogin) {
    // Communicate with Java
    console.log('Logging in...');
    loginFetch();
  }
  else {
    // Populate dynamic content
    isLogin = !isLogin;
    clearInputContainer();
    const inputContainer = document.getElementById('inputContainer');
    inputContainer.innerHTML = `
      <form class="login">
        <p>Username</p><input id="username" type="text"/>
        <p>Password</p><input id="password" type="password"/> 
        <div>
            <button class="btn" onclick="login()">Login</button>
            <button class="btn" onclick="signup()">Sign Up</button>
        </div>
      </form>`;
  }
}

/**
 * Login functionality communicating with the backend
 */
function loginFetch() {  
  const trainerData = {
    username: document.getElementById("username").value,
    password: document.getElementById("password").value
  };
  
  // Communicate with Java
  fetch('/trainer/login', {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json'
    },
    body: JSON.stringify(trainerData) 
  })
  .then(response => {
    if (response.ok) {
      return response.text();
    } 
    else {
      throw new Error("Login failed: " + response.status);
    }
  })
  .then(result => {
    // Successful Log In
    document.getElementById("userContainer").style.display = "none";
    console.log(result);
  })
  .catch(error => {
    console.error("Error Logging In:", error); // Handle errors
  });
}

/**
 * Clears the input container to show either the
 * login or sign up dynamic content
 */
function clearInputContainer() {
  const inputContainer = document.getElementById('inputContainer');
  inputContainer.innerHTML = '';
}