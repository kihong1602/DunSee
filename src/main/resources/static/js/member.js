const SUCCESS = "SUCCESS"
const FAILURE = "FAILURE"

function loginProcess() {
  let username = document.getElementById('username');
  let password = document.getElementById('password');

  const data = setLoginUserData(username, password);

  fetch('/login-process', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  })
  .then(response => response.text())
  .then(result => {
    const jsonData = JSON.parse(result);
    console.log(jsonData.code);
    console.log(jsonData.description);
    console.log(jsonData.data.value);
    const detail = jsonData.detail
    switch (jsonData.data.value) {
      case SUCCESS: {
        alert("로그인에 성공하였습니다.")
        location.href = detail
        break
      }
      case FAILURE : {
        alert(detail)
        username.value = '';
        password.value = '';
        break
      }
    }
  })
}

function registerProcess() {
  let username = document.getElementById('username');
  let password = document.getElementById('password');
  let email = document.getElementById('email');

  const data = setJoinUserData(username, password, email);

  fetch('/register-process', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  })
  .then(response => response.text())
  .then(result => {
    const jsonData = JSON.parse(result);
    console.log(jsonData.code);
    console.log(jsonData.description);
    console.log(jsonData.data.value);
    const detail = jsonData.detail
    switch (jsonData.data.value) {
      case SUCCESS: {
        alert(detail)
        location.href = "/login"
        break
      }
      case FAILURE : {
        alert("회원가입에 실패하였습니다. 다시한번 확인해주세요.")
        username.value = '';
        password.value = '';
        email.value = '';
        break
      }
    }
  })
}

function setJoinUserData(username, password, email) {
  return {
    username: username.value,
    password: password.value,
    email: email.value
  }
}

function setLoginUserData(username, password) {
  return {username: username.value, password: password.value}
}