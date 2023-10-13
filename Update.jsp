<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*,p1.Employee" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <style>
    body {
      background-color: #FFA07A; /* Light gray background */
    }
    .card {
      background-color: #bd1515; /* White card background */
    }
  </style>
  <title>Bootstrap Form Example</title>
</head>
<body>

<div class="container mt-5">
  <div class="row justify-content-center">
    <div class="col-md-6">
      <div class="card">
        <div class="card-header" id="cardHeader" style="background-color: #007bff; color: #141313;">
         <h3> <b>Update Page</b></h3>
        </div>
        <div class="card-body" id="cardBody" style="background-color: rgb(196, 250, 116);">
          <form action="servlet3" method="GET" id="validationForm3">
            <div class="form-group">
              <label for="id">ID</label>
              <input type="text" class="form-control"  name="t1"  placeholder="Enter ID" value='<%= request.getParameter("id")%>' readonly=true />
            </div>
            <div class="form-group">
              <label for="name">Name</label>
              <input type="text" class="form-control"  name="t2" id="name" placeholder="Enter Name" value='<%= request.getParameter("name")%>' required / >
            </div>
            <div class="form-group">
              <label for="address">Address</label>
              <input type="text" class="form-control"  name="t3" id="address" placeholder="Enter Address" value='<%= request.getParameter("addr")%>' required/>
            </div>
            <div class="form-group">
              <label for="salary">Salary</label>
              <input type="number" class="form-control"  name="t4" id="salary" placeholder="Enter Salary" value='<%= request.getParameter("sal")%>' required/ >
            </div>
            <div class="form-group">
              <label for="email">Email</label>
              <input type="email" class="form-control"  name="t5" id="email" placeholder="Enter Email" value='<%= request.getParameter("email")%>' required/ >
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
          </form>
        </div>
      </div>  
      
    </div>
  </div>
</div>


<script>
    
  document.getElementById("validationForm3").addEventListener("submit", function (event) {
    const name = document.getElementById("name").value;
    const address = document.getElementById("address").value;
    const salary = document.getElementById("salary").value;
    const email = document.getElementById("email").value;

    if (!validateName(name)) {
      alert("Name is not valid");
      event.preventDefault();
    }

    if (!validateAddress(address)) {
      alert("Address is not valid");
      event.preventDefault();
    }

    if (!validateSalary(salary)) {
      alert("Salary is not valid");
      event.preventDefault();
    }

    if (!validateEmail(email)) {
      alert("Email is not valid");
      event.preventDefault();
    }
  });

  function validateName(name) {
    const regex = /^[a-zA-Z\s]{1,30}$/;
    return regex.test(name);
  }

  function validateAddress(address) {
    const regex = /^[A-Za-z\s]{1,30}$/;
    return regex.test(address);
  }

  function validateSalary(salary) {
    return !isNaN(salary) && salary >= 10000 && salary <= 200000;
  }

  function validateEmail(email) {
    const regex = /^[a-zA-Z0-9._-]+@gmail\.com$/;
    return regex.test(email);
  }
  history.pushState(null, null, location.href);
      window.onpopstate = function () {
          history.go(1);
      };
</script>



</body>
</html>
