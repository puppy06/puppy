<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Weather Monitoring Dashboard</title>
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.map"></script>
  
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
   
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
    	  var vdata = [];
    	    	var time = [];
    	    	var temperature = [];
    	    	 var Header= new Array(2);
    	    	 Header[0] = 'Year';
    	    	 Header[1] = 'Toronto';
 	            vdata.push(Header);
    	            $.ajax({
    	                 // url:"https://api.openweathermap.org/data/2.5/weather?lat=43.651890&lon=-79.381706&appid=716f8ae7ce8ffc9bcd3b65eda5e5a71f",
    	                 url:"https://archive-api.open-meteo.com/v1/era5?latitude=43.4643&longitude=-80.5204&start_date=2019-12-19&end_date=2024-12-22&daily=temperature_2m_max",
    	                 type: "GET",
    	                  success: function (data) {
    	                	//  const myObj = JSON.parse(data);
    	                	var test =data.daily;
    	                	time = test.time;
    	                	temperature = test.temperature_2m_max;
    	                    for (var i = 0; i < temperature.length; i++) {
    	    	                 var temp= new Array(2);
    	    	                 temp[0] = time[i];
    	    	                 temp[1] = temperature[i];
    	    	                 // temp.push(time[i]);
    	    	                 // temp.push(temperature[i]);
    	    	                 vdata.push(temp);
    	    	                 // alert(vdata[i]);
    	    	             }
    	      	            console.log('Vdata is: ', vdata);
    	      	          var v2data = google.visualization.arrayToDataTable(vdata);           
    	    	 
    	        var options = {
    	          title: 'Temperature changes in Toronto in last 5 years',
    	          curveType: 'function',
    	          legend: { position: 'bottom' }
    	        };

    	        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));
    	        chart.draw(v2data, options);
    	                  }
    	                 
    	               });
    	            
  
       }
      
    </script>
    <script type="text/javascript">
      google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
      /*  var data = google.visualization.arrayToDataTable([
          ['Temperature', 'Percentage'],
          ['Above 20 °C',     11],
          ['Between 10 °C to 20 °C',      2],
          ['Between 0 °C to 10 °C',  2],
          ['Between -10 °C to 0 °C', 2],
          ['Below -10 °C',    7]
        ]);*/
        var p1 = 0;
        var p2 = 0;
        var p3 = 0;
        var p4 = 0;
        var p5 = 0;
        var pdata = [];
    	var time = [];
    	var temperature = [];
    	 var Header= new Array(2);
    	 Header[0] = 'Year';
    	 Header[1] = 'Toronto';
         pdata.push(Header);
        $.ajax({
             url:"https://archive-api.open-meteo.com/v1/era5?latitude=43.4643&longitude=-80.5204&start_date=2019-01-01&end_date=2024-12-22&daily=temperature_2m_max",
            type: "GET",
             success: function (data) {
           	var test =data.daily;
           	time = test.time;
           	temperature = test.temperature_2m_max;
               for (var i = 0; i < temperature.length; i++) {
	               //  var temp= new Array(2);
	               //  temp[0] = time[i];
	                // temp[1] = temperature[i];
	                 if(Number(temperature[i])>=20) p1++;
	                 if(Number(temperature[i])<20 && Number(temperature[i])>=10) p2++;
	                 if(Number(temperature[i])<10 && Number(temperature[i])>=0) p3++;
	                 if(Number(temperature[i])<0 && Number(temperature[i])>=-10) p4++;
	                 if(Number(temperature[i])<=-10 ) p5++;
               }
               	var t1 = new Array(2);
               	t1[0] = 'Above 20 °C';
               	t1[1] = p1;
               	pdata.push(t1);
               	var t2 = new Array(2);
               	t2[0] = 'Between 10 °C to 20 °C';
               	t2[1] = p2;
            	pdata.push(t2);
              	var t3 = new Array(2);
               	t3[0] = 'Between 0 °C to 10 °C';
               	t3[1] = p3;
            	pdata.push(t3);
              	var t4 = new Array(2);
               	t4[0] = 'Between -10 °C to 0 °C';
               	t4[1] = p4;
            	pdata.push(t4);
              	var t5 = new Array(2);
               	t5[0] = 'Below -10 °C';
               	t5[1] = p5;
            	pdata.push(t5);
 	            console.log('Pdata is: ', pdata);
 	          var p2data = google.visualization.arrayToDataTable(pdata);           


 	         var options = {
 	           title: 'Temperature Distributions in Toronto in 2024',
 	           is3D: true,
 	          width:400,
               height:300,
 	         };
 	        var barchart_options = {title:'Temperature Distributions in Toronto in 2024',
 	                width:400,
 	                height:300,
 	                legend: 'none'};
 	         var chart = new google.visualization.PieChart(document.getElementById('myPieChart'));
 	         chart.draw(p2data, options);
 	        var barchart = new google.visualization.BarChart(document.getElementById('barchart_div'));
 	       barchart.draw(p2data, barchart_options);
             }
             
           
       

        
        });
     

}
    </script>
    </head>
    <body class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
              <a class="navbar-brand ps-3" href="charts.html">Weather Monitoring Dashboard</a>
            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
            <!-- Navbar Search-->
            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
                <div class="input-group">
                    <input class="form-control" type="text" placeholder="Search for..." aria-label="Search for..." aria-describedby="btnNavbarSearch" />
                    <button class="btn btn-primary" id="btnNavbarSearch" type="button"><i class="fas fa-search"></i></button>
                </div>
            </form>
            <!-- Navbar-->
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4" style="display: none">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#!">Settings</a></li>
                        <li><a class="dropdown-item" href="#!">Activity Log</a></li>
                        <li><hr class="dropdown-divider" /></li>
                        <li><a class="dropdown-item" href="#!">Logout</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <div class="sb-sidenav-menu-heading"></div>
                            <a class="nav-link" href="charts.html">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Dashboard
                            </a>
                            <div class="sb-sidenav-menu-heading" style="display: none">Interface</div>
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                                <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                                Layouts
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div style="display: none" class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="layout-static.html">Static Navigation</a>
                                    <a class="nav-link" href="layout-sidenav-light.html">Light Sidenav</a>
                                </nav>
                            </div>
                            <a class="nav-link collapsed" style="display: none" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages">
                                <div class="sb-nav-link-icon"><i class="fas fa-book-open"></i></div>
                                Pages
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div style="display: none" class="collapse" id="collapsePages" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                                    <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#pagesCollapseAuth" aria-expanded="false" aria-controls="pagesCollapseAuth">
                                        Authentication
                                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                                    </a>
                                    <div class="collapse" id="pagesCollapseAuth" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordionPages">
                                        <nav class="sb-sidenav-menu-nested nav">
                                            <a class="nav-link" href="login.html">Login</a>
                                            <a class="nav-link" href="register.html">Register</a>
                                            <a class="nav-link" href="password.html">Forgot Password</a>
                                        </nav>
                                    </div>
                                    <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#pagesCollapseError" aria-expanded="false" aria-controls="pagesCollapseError">
                                        Error
                                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                                    </a>
                                    <div class="collapse" id="pagesCollapseError" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordionPages">
                                        <nav class="sb-sidenav-menu-nested nav">
                                            <a class="nav-link" href="401.html">401 Page</a>
                                            <a class="nav-link" href="404.html">404 Page</a>
                                            <a class="nav-link" href="500.html">500 Page</a>
                                        </nav>
                                    </div>
                                </nav>
                            </div>

                    </div>
                  
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Weather in Toronto</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">Statistical Charts for Weather History in Toronto Canada</li>
                        </ol>
           
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-chart-area me-1"></i>
                                Temperature changes in Toronto in last 5 years

                            </div>
                            <div class="card-body"><div id="curve_chart" width="100%" height="30"></div></div>
                            <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
                        </div>
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="card mb-4">
                                    <div class="card-header">
                                        <i class="fas fa-chart-bar me-1"></i>
                                        Bar Chart: Temperature Distributions in last 5 years in Toronto
                                  
                                    </div>
                                    <div class="card-body"><div id="barchart_div" width="100%" height="50"></div></div>
                                    <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="card mb-4">
                                    <div class="card-header">
                                        <i class="fas fa-chart-pie me-1"></i>
                                       Pie Chart: Temperature Distributions in last 5 years in Toronto
                                    </div>
                                    <div class="card-body"><div id="myPieChart" width="100%" height="50"></div></div>
                                    <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
 
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="assets/demo/chart-area-demo.js"></script>
        <script src="assets/demo/chart-bar-demo.js"></script>
      <!--    <script src="assets/demo/chart-pie-demo.js"></script>-->
    </body>
</html>
