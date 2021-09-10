let loginForm = document.getElementById("login-form");
 
//gets session information
  window.onload = async function(){
    const sessionRes = await fetch(`${domain}/api/check-session`)
    const sessionUserData = await sessionRes.json();
    let sessionInfo = sessionUserData.data;


        if(sessionInfo.userRoleIdFk === 1){
            window.location = `${domain}/employee?id=${sessionInfo.userId}&roleId=${sessionInfo.userRoleIdFk}`
            }
        if(sessionInfo.userRoleIdFk === 2){
                window.location = `${domain}/financeManager?id=${sessionInfo.userId}&roleId=${sessionInfo.userRoleIdFk}`
            }
}  

//login form
loginForm.onsubmit = async function(e){
    e.preventDefault();

    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;

    let response = await fetch(`${domain}/api/login`,{
        method: "POST",
        body: JSON.stringify({
            username: username,
            password: password
        })
    })

    //based on the response itll either send to window location based on Role Id
    //or if failed display error message.
    let responseData = await response.json();
        let userInfo = responseData.data;
      if(responseData.success){
        if(userInfo.userRoleIdFk === 1){
        window.location = `${domain}/employee?id=${userInfo.userId}&roleId=${userInfo.userRoleIdFk}`
        }
        if(userInfo.userRoleIdFk === 2){
            window.location = `${domain}/financeManager?id=${userInfo.userId}&roleId=${userInfo.userRoleIdFk}`
        }
    }else{
       let failedLoginElem = document.getElementById("incorrect")
       failedLoginElem.style.visibility = "visible";
    } 
}