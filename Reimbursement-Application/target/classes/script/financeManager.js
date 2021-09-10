//gets id from query param
const urlParams = new URLSearchParams(window.location.search)
const userId = urlParams.get("id")
const roleId = urlParams.get("roleId")

//W3 school tab handler
function openReimb(evt, status) {
  var i, tabcontent, tablinks;
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }
  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }
  document.getElementById(status).style.display = "block";
  evt.currentTarget.className += " active";
}

//checks session
 window.onload = async function(){
  const sessionRes = await fetch(`${domain}/api/check-session`)
  const sessionUserData = await sessionRes.json();
  let sessionInfo = sessionUserData.data;

  console.log(sessionInfo)
   if(sessionInfo){
      if(sessionInfo.userId != userId){
        window.location = `${domain}/`;
      }
    }
    if(sessionInfo.userRoleIdFk != 2){
      window.location = `${domain}/`;
    }
       showReimbursements();      
}

//loads all the reimbursments for all employees and loads employees
    async function showReimbursements(){
        const listResponse = await fetch(`${domain}/api/finance/reimbursement`)
        const listData = await listResponse.json(); 
        const employeeResponse = await fetch(`${domain}/api/getAllUsers`)
        const employeeData = await employeeResponse.json();


    //adds employees to drop down menu
    let dropDownElem = document.getElementById("inputGroupSelect01");
   
    //adds option for all reimbursments to be shown again
     let allEmpListElem = document.createElement("option")
     allEmpListElem.value = 0;
     allEmpListElem.innerText = "All Employees";
    dropDownElem.appendChild(allEmpListElem);


    //loads the employees to drop down
    employeeData.data.forEach(employee =>{
      let nameListElem = document.createElement("option")
          nameListElem.value = employee.userId
          nameListElem.innerHTML = employee.firstName;
          dropDownElem.appendChild(nameListElem);

    }) 

    //create parent elements
    let AllReimburListElem = document.getElementById("AllReimburList");
    let reimburListElem = document.getElementById("reimburList");
    let approvedListElem = document.getElementById("approvedList");
    let deniedListElem = document.getElementById("deniedList");

    //filters by status
    listData.data.forEach(reimbur =>{
      let status;
      if(reimbur.reimburStatusFk == 1){
          status = "Pending"
      }
      if(reimbur.reimburStatusFk == 2){
          status = "Approved"
      }
      if(reimbur.reimburStatusFk == 3){
          status = "Denied"
      }

      //start of ALL REIMBURSMENT LIST
      let listOfReimbElem = document.createElement("div")
        
        listOfReimbElem.className = "list-of-reimb";
        let descriptElem = document.createElement("div")
        descriptElem.className = "description"
        descriptElem.innerHTML = reimbur.reimburDescription;

        let amountElem = document.createElement("div")
        var conVertDisp = (reimbur.reimburAmount / 100);
        amountElem.className = "amount"
        amountElem.innerHTML = conVertDisp;

        let statusElem = document.createElement("div")
        statusElem.className = "statue"
        statusElem.innerHTML = status;

        let viewBtnElem = document.createElement("button");
        viewBtnElem.innerText = "View Item";
        viewBtnElem.className = "btn btn-primary";
        viewBtnElem.id = "view-reimburAll";

        listOfReimbElem.appendChild(descriptElem);
        listOfReimbElem.appendChild(amountElem);
        listOfReimbElem.appendChild(statusElem);
  
        //clone is created to lessen the requests made to the back end
        var clone = listOfReimbElem.cloneNode(true);

        let allReCondElm = document.createElement("div")
        allReCondElm.className = "allReCond"

        allReCondElm.appendChild(listOfReimbElem);
        allReCondElm.appendChild(viewBtnElem);

        AllReimburListElem.appendChild(allReCondElm);

        viewBtnElem.onclick = function(){
          window.location = `${domain}/reimbursment?reimburId=${reimbur.reimburId}&userId=${userId}&roleId=${roleId}`
        }
        //start of ALL PENDING LIST
        if(status == "Pending"){
          //create parent clone element
          let allPendingElm = document.createElement("div")
          allPendingElm.className = "allPending"

          allPendingElm.appendChild(clone);


          //Button has to be recreated and appended (Clone doesn't take attributes with it)
          let viewBtnElem = document.createElement("button");
          viewBtnElem.innerText = "View Item";
          viewBtnElem.className = "btn btn-primary";
          viewBtnElem.id = "view-reimburP";
          allPendingElm.appendChild(viewBtnElem);

          reimburListElem.appendChild(allPendingElm);

          viewBtnElem.onclick = function(){
            window.location = `${domain}/reimbursment?reimburId=${reimbur.reimburId}&userId=${userId}&roleId=${roleId}`
          }
        }
        //start of ALL APPROVED LIST
        if(status == "Approved"){
          //create parent clone element
          let allApprovedElm = document.createElement("div")
          allApprovedElm.className = "allAppElm"

          allApprovedElm.appendChild(clone);
          let viewBtnElem = document.createElement("button");
          viewBtnElem.innerText = "View Item";
          viewBtnElem.className = "btn btn-primary";
          viewBtnElem.id = "view-reimburA";
          allApprovedElm.appendChild(viewBtnElem);

          approvedListElem.appendChild(allApprovedElm);

          //Button has to be recreated and appended (Clone doesn't take attributes with it)
          viewBtnElem.onclick = function(){
            window.location = `${domain}/reimbursment?reimburId=${reimbur.reimburId}&userId=${userId}&roleId=${roleId}`
          }
        }
        //start of ALL DENIED LIST
        if(status == "Denied"){
          //create parent clone element
          let allDeniedElm = document.createElement("div")
          allDeniedElm.className = "allDeniedElm"


          allDeniedElm.appendChild(clone);
          let viewBtnElem = document.createElement("button");
          viewBtnElem.innerText = "View Item";
          viewBtnElem.className = "btn btn-primary";
          viewBtnElem.id = "view-reimburD";


          allDeniedElm.appendChild(viewBtnElem);

          deniedListElem.appendChild(allDeniedElm);

          //Button has to be recreated and appended (Clone doesn't take attributes with it)
          viewBtnElem.onclick = function(){
            window.location = `${domain}/reimbursment?reimburId=${reimbur.reimburId}&userId=${userId}&roleId=${roleId}`
          }
        }
      })

    }


    //button to filter reimbursments by employee
    filterReimb.onclick = function(){
      var select = document.getElementById("inputGroupSelect01");
      var filteredValue = select.options[select.selectedIndex].value; 
      
      //Clear elements and reload new information
      document.getElementById("AllReimburList").innerText="";
      document.getElementById("reimburList").innerText="";
      document.getElementById("approvedList").innerText="";
      document.getElementById("deniedList").innerText="";

      if(filteredValue == 0){
        //have to remove the element and reload it or it will add on to it each time
         document.getElementById("inputGroupSelect01").innerText="";
        showReimbursements();
      }
      if(filteredValue != 0){

        showFilteredReimbursements(filteredValue);
      }
    } 
 

    //Same function as above but for the filtered employee
    async function showFilteredReimbursements(filteredValue){
      //get requests
      const listResponse = await fetch(`${domain}/api/allReimbur?userId=${filteredValue}`)
      const listData = await listResponse.json(); 
      const employeeResponse = await fetch(`${domain}/api/getAllUsers`)
      const employeeData = await employeeResponse.json();
      
      //create parent elements
  let AllReimburListElem = document.getElementById("AllReimburList");
  let reimburListElem = document.getElementById("reimburList");
  let approvedListElem = document.getElementById("approvedList");
  let deniedListElem = document.getElementById("deniedList");

  //filter results
  listData.data.forEach(reimbur =>{
    let status;
    if(reimbur.reimburStatusFk == 1){
        status = "Pending"
    }
    if(reimbur.reimburStatusFk == 2){
        status = "Approved"
    }
    if(reimbur.reimburStatusFk == 3){
        status = "Denied"
    }

    //create parent elements
    let listOfReimbElem = document.createElement("div")
        
        listOfReimbElem.className = "list-of-reimb";
        let descriptElem = document.createElement("div")
        descriptElem.className = "description"
        descriptElem.innerHTML = reimbur.reimburDescription;

        let amountElem = document.createElement("div")
        var conVertDisp = (reimbur.reimburAmount / 100);
        amountElem.className = "amount"
        amountElem.innerHTML = conVertDisp;

        let statusElem = document.createElement("div")
        statusElem.className = "statue"
        statusElem.innerHTML = status;

        let viewBtnElem = document.createElement("button");
        viewBtnElem.innerText = "View Item";
        viewBtnElem.className = "btn btn-primary";
        viewBtnElem.id = "view-reimburAll";

        listOfReimbElem.appendChild(descriptElem);
        listOfReimbElem.appendChild(amountElem);
        listOfReimbElem.appendChild(statusElem);
  

        var clone = listOfReimbElem.cloneNode(true);

        let allReCondElm = document.createElement("div")
        allReCondElm.className = "allReCond"

        allReCondElm.appendChild(listOfReimbElem);
        allReCondElm.appendChild(viewBtnElem);

        AllReimburListElem.appendChild(allReCondElm);

        viewBtnElem.onclick = function(){
          window.location = `${domain}/reimbursment?reimburId=${reimbur.reimburId}&userId=${userId}&roleId=${roleId}`
        }
        if(status == "Pending"){
          let allPendingElm = document.createElement("div")
          allPendingElm.className = "allPending"

          allPendingElm.appendChild(clone);


    
          let viewBtnElem = document.createElement("button");
          viewBtnElem.innerText = "View Item";
          viewBtnElem.className = "btn btn-primary";
          viewBtnElem.id = "view-reimburP";
          allPendingElm.appendChild(viewBtnElem);

          reimburListElem.appendChild(allPendingElm);

          viewBtnElem.onclick = function(){
            window.location = `${domain}/reimbursment?reimburId=${reimbur.reimburId}&userId=${userId}&roleId=${roleId}`
          }
        }
        if(status == "Approved"){
          let allApprovedElm = document.createElement("div")
          allApprovedElm.className = "allAppElm"

          allApprovedElm.appendChild(clone);
          let viewBtnElem = document.createElement("button");
          viewBtnElem.innerText = "View Item";
          viewBtnElem.className = "btn btn-primary";
          viewBtnElem.id = "view-reimburA";
          allApprovedElm.appendChild(viewBtnElem);

          approvedListElem.appendChild(allApprovedElm);

          viewBtnElem.onclick = function(){
            window.location = `${domain}/reimbursment?reimburId=${reimbur.reimburId}&userId=${userId}&roleId=${roleId}`
          }
        }
        if(status == "Denied"){
          let allDeniedElm = document.createElement("div")
          allDeniedElm.className = "allDeniedElm"


          allDeniedElm.appendChild(clone);
          let viewBtnElem = document.createElement("button");
          viewBtnElem.innerText = "View Item";
          viewBtnElem.className = "btn btn-primary";
          viewBtnElem.id = "view-reimburD";


          allDeniedElm.appendChild(viewBtnElem);

          deniedListElem.appendChild(allDeniedElm);

        viewBtnElem.onclick = function(){
          window.location = `${domain}/reimbursment?reimburId=${reimbur.reimburId}&userId=${userId}&roleId=${roleId}`
        }
      }
    })

  }

  //log out button ends session
  let logoutBtn = document.getElementById("logout-btn")
  logoutBtn.onclick = async function(){
      let logoutResult = await fetch(`${domain}/api/logout`)
 
      let logoutData = await logoutResult.json();
 
      if(logoutData.success)
      window.location = `${domain}/`;
  }