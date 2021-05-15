<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="en">
<!--<![endif]-->

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>BachMek - Menu Admin</title>
<meta name="description" content="Sufee Admin - HTML5 Admin Template">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="apple-touch-icon" href="apple-icon.png">
<link rel="shortcut icon" href="favicon.ico">

<link rel="stylesheet"
	href="vendors/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="vendors/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet"
	href="vendors/themify-icons/css/themify-icons.css">
<link rel="stylesheet"
	href="vendors/flag-icon-css/css/flag-icon.min.css">
<link rel="stylesheet" href="vendors/selectFX/css/cs-skin-elastic.css">
<link rel="stylesheet" href="vendors/jqvmap/dist/jqvmap.min.css">


<link rel="stylesheet" href="assets/css/style.css">

<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800'
	rel='stylesheet' type='text/css'>

</head>

<body>


	<!-- Left Panel -->
	<aside id="left-panel" class="left-panel">
		<nav class="navbar navbar-expand-sm navbar-default">

			<div class="navbar-header">
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#main-menu" aria-controls="main-menu"
					aria-expanded="false" aria-label="Toggle navigation">
					<i class="fa fa-bars"></i>
				</button>
				<a class="navbar-brand" href="HomeA.php"><img
					src="images/logo.png" alt="Logo"></a> <a
					class="navbar-brand hidden" href="HomeA.php"><img
					src="images/logo2.png" alt="Logo"></a>
			</div>

			<div id="main-menu" class="main-menu collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<div>
						<li class="active"><a href="HomeA.php"> <i
								class="menu-icon fa ti-home"></i>Home page
						</a></li>
					</div>

					<h3 class="menu-title">Gestions</h3>
					<!-- /.menu-title -->
					<li class="menu-item-has-children dropdown"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> <i
							class="menu-icon fa fa-users"></i>Des Etudiants
					</a>
						<ul class="sub-menu children dropdown-menu">
							<li><i class="fa fa-plus-square-o"></i><a
								href="AjouterEtudiant.php">Ajout</a></li>
							<li><i class="fa ti-search"></i><a
								href="RechercheEtudiant.php">Recherche</a></li>
						</ul></li>
					<li class="menu-item-has-children dropdown"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> <i
							class="menu-icon fa  fa-check-square-o"></i>D'Absences
					</a>
						<ul class="sub-menu children dropdown-menu">
							<li><i class="fa fa-plus-square"></i><a href="GetFiliereSemestre.php">D'une
									classe</a></li>
							<li><i class="fa fa-plus-square-o"></i><a href="GetFiliereSemestreEtud.php">D'un Etudiant</a></li> 
							<li><i class="fa ti-search"></i><a href="RechercheAbs.php">Recherche</a></li>
						</ul></li>
					<li class="menu-item-has-children dropdown"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> <i
							class="menu-icon fa fa-building-o"></i>Des Reservations
					</a>
						<ul class="sub-menu children dropdown-menu">
							<li><i class="fa fa-plus-square-o"></i><a href="AjouterReservation.php">Ajout</a></li>
							<li><i class="fa ti-search"></i><a href="RechercheReservation.php">Recherche</a></li>
							<li><i class="fa ti-list-ol"></i><a href="ListAllReservation.php">List</a></li>
						</ul></li>
					<h3 class="menu-title">Consultation des Listes</h3>
					<!-- /.menu-title -->
					<li class="menu-item-has-children dropdown"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> <i
							class="menu-icon fa fa-users"></i>Etudiants
					</a>
						<ul class="sub-menu children dropdown-menu">
						
							<li><i class="fa ti-list-ol"></i><a
								href="ListAllEtudiant.php">List All Etudiants</a></li>
							<li><i class="fa ti-menu-alt"></i><a
								href="ListEtudiantPar.php">List Etudiants Par</a></li>
						</ul></li>

					<li class="menu-item-has-children dropdown"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> <i
							class="menu-icon fa fa-check-square-o"></i>Abs
					</a>
						<ul class="sub-menu children dropdown-menu">
							<li><i class="fa ti-layout-list-thumb"></i><a href="ListAllAbs.php">All Abs</a></li>
							<li><i class="fa ti-layout-menu-v"></i><a href="ListAbsSupp.php">Abs Supprimee </a></li>
							<li><i class="fa fa-calendar"></i><a href="AbsCal.php">Abs Par Calendrier </a></li>
							<li><i class="fa fa-calendar"></i><a href="AbsSuppCal.php">Abs Supp Par Calendrier </a></li>
						</ul></li>
					
					<li class="menu-item-has-children dropdown"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> <i
							class="menu-icon fa ti-check-box"></i>Reservations
					</a>
						<ul class="sub-menu children dropdown-menu">
							<li><i class="fa ti-list-ol"></i><a href="">.....</a></li>
							<li><i class="fa ti-layout-list-thumb"></i><a href=""> ..... </a></li>
							<li><i class="fa ti-layout-menu-v"></i><a href="">.... </a></li>
						</ul></li>
					<li class="menu-item-has-children dropdown"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> <i
							class="menu-icon fa  fa-spinner"></i>Other
					</a>
						<ul class="sub-menu children dropdown-menu">
							<li><i class="fa fa-user"></i><a href="ListAllParent.php">Parents</a></li>
							<li><i class="fa ti-list"></i><a href="ListAllFiliere.php">Filieres</a></li>
							<li><i class="fa fa-list-alt"></i><a href="ListAllSemestre.php">Semestre</a></li>
							<li><i class="fa ti-menu-alt"></i><a href="ListAllModule.php">Modules</a></li>
							<li><i class="fa fa-users"></i><a href="ListAllProf.php">Profs</a></li>
							<li><i class="fa fa-file-text-o"></i><a href="ListAllInscription.php">Inscriptions</a></li>
							<li><i class="fa fa-building-o"></i><a href="ListAllSalle.php">Salles</a></li>
							<li><i class="fa fa-filter"></i><a href="ListAllEquipement.php">Equipement</a></li>
							<li><i class="fa fa-calendar"></i><a href="ListAllPresProf.php">Presence Prof</a></li>
						</ul></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</nav>
	</aside>
	<!-- /#left-panel -->

	<!-- Left Panel -->

	<!-- Right Panel -->

	<div id="right-panel" class="right-panel">

		<!-- Header-->
		<header id="header" class="header">

			<div class="header-menu">

				<div class="col-sm-7">
					<a id="menuToggle" class="menutoggle pull-left"><i
						class="fa fa fa-tasks"></i></a>
					<div class="header-left">
						<button class="search-trigger">
							<i class="fa fa-search"></i>
						</button>
						<div class="form-inline">
							<form class="search-form">
								<input class="form-control mr-sm-2" type="text"
									placeholder="Search ..." aria-label="Search">
								<button class="search-close" type="submit">
									<i class="fa fa-close"></i>
								</button>
							</form>
						</div>

						<div class="dropdown for-notification">
							<button class="btn btn-secondary dropdown-toggle" type="button"
								id="notification" data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">
								<i class="fa fa-bell"></i> <span class="count bg-danger">5</span>
							</button>
							<div class="dropdown-menu" aria-labelledby="notification">
								<p class="red">You have 3 Notification</p>
								<a class="dropdown-item media bg-flat-color-1" href="#"> <i
									class="fa fa-check"></i>
									<p>Server #1 overloaded.</p>
								</a> <a class="dropdown-item media bg-flat-color-4" href="#"> <i
									class="fa fa-info"></i>
									<p>Server #2 overloaded.</p>
								</a> <a class="dropdown-item media bg-flat-color-5" href="#"> <i
									class="fa fa-warning"></i>
									<p>Server #3 overloaded.</p>
								</a>
							</div>
						</div>

						<div class="dropdown for-message">
							<button class="btn btn-secondary dropdown-toggle" type="button"
								id="message" data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">
								<i class="ti-email"></i> <span class="count bg-primary">9</span>
							</button>
							<div class="dropdown-menu" aria-labelledby="message">
								<p class="red">You have 4 Mails</p>
								<a class="dropdown-item media bg-flat-color-1" href="#"> <span
									class="photo media-left"><img alt="avatar"
										src="images/avatar/1.jpg"></span> <span
									class="message media-body"> <span
										class="name float-left">Jonathan Smith</span> <span
										class="time float-right">Just now</span>
										<p>Hello, this is an example msg</p>
								</span>
								</a> <a class="dropdown-item media bg-flat-color-4" href="#"> <span
									class="photo media-left"><img alt="avatar"
										src="images/avatar/2.jpg"></span> <span
									class="message media-body"> <span
										class="name float-left">Jack Sanders</span> <span
										class="time float-right">5 minutes ago</span>
										<p>Lorem ipsum dolor sit amet, consectetur</p>
								</span>
								</a> <a class="dropdown-item media bg-flat-color-5" href="#"> <span
									class="photo media-left"><img alt="avatar"
										src="images/avatar/3.jpg"></span> <span
									class="message media-body"> <span
										class="name float-left">Cheryl Wheeler</span> <span
										class="time float-right">10 minutes ago</span>
										<p>Hello, this is an example msg</p>
								</span>
								</a> <a class="dropdown-item media bg-flat-color-3" href="#"> <span
									class="photo media-left"><img alt="avatar"
										src="images/avatar/4.jpg"></span> <span
									class="message media-body"> <span
										class="name float-left">Rachel Santos</span> <span
										class="time float-right">15 minutes ago</span>
										<p>Lorem ipsum dolor sit amet, consectetur</p>
								</span>
								</a>
							</div>
						</div>
					</div>
				</div>

				<div class="col-sm-5">
					<div class="user-area dropdown float-right">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"> <img
							class="user-avatar rounded-circle" src="images/admin.jpg"
							alt="User Avatar">
						</a>

						<div class="user-menu dropdown-menu">
							<a class="nav-link" href="#"><i class="fa fa-user"></i> My
								Profile</a> <a class="nav-link" href="#"><i class="fa fa-user"></i>
								Notifications <span class="count">13</span></a> <a class="nav-link"
								href="#"><i class="fa fa-cog"></i> Settings</a> <a
								class="nav-link" href="deconnexion.php"><i
								class="fa fa-power-off"></i> Log-out</a>
						</div>
					</div>

					<div class="language-select dropdown" id="language-select">
						<a class="dropdown-toggle" href="#" data-toggle="dropdown"
							id="language" aria-haspopup="true" aria-expanded="true"> <i
							class="flag-icon flag-icon-us"></i>
						</a>
						<div class="dropdown-menu" aria-labelledby="language">
							<div class="dropdown-item">
								<span class="flag-icon flag-icon-fr"></span>
							</div>
							<div class="dropdown-item">
								<i class="flag-icon flag-icon-es"></i>
							</div>
							<div class="dropdown-item">
								<i class="flag-icon flag-icon-us"></i>
							</div>
							<div class="dropdown-item">
								<i class="flag-icon flag-icon-it"></i>
							</div>
						</div>
					</div>

				</div>
			</div>

		</header>
		<!-- /header -->
		<!-- Header-->

		<div class="breadcrumbs">
			<div class="col-sm-4">
				<div class="page-header float-left">
					<div class="page-title">
						<h1>Module</h1>
					</div>
				</div>
			</div>
			<div class="col-sm-8">
				<div class="page-header float-right">
					<div class="page-title">
						<ol class="breadcrumb text-right">
							<li class="active">Module</li>
						</ol>
					</div>
				</div>
			</div>
		</div>

		<div class="content mt-3">
			<form method="POST" action="AddInsc.php">
				<div class="animated fadeIn">
					<div class="row">
						<div class="col-md-12">
							<div class="card">
								<div class="card-header">
									<strong class="card-title">Ajouter une Inscription</strong>
								</div>
								<div class="card-body">
									<div class="card-body card-block">
										
										<div class="form-group">
											<div class="input-group">
												<div class="input-group-addon">
													<i class="fa ti-view-list"></i>
												</div>
												<select class="form-control" name="NomF" required>
													<option value="" selected hidden>Filiere</option>
													<c:forEach items="${Mother.getListfili()}" var="fil">
														<option>${fil.getNomF()}</option>
													</c:forEach>
												</select> <span class="select-arrow"></span>
											</div>
											<small class="form-text text-muted">ex. BGI</small>
										</div>
										<div class="form-group">
											<div class="input-group">
												<div class="input-group-addon">
													<i class="fa fa-asterisk"></i>
												</div>
												<input class="form-control" type="text" name="CNIE"
													placeholder="CNI Etudiant" required>
												<%-- <select class="form-control" name="CNIE" required>
													<option value="" selected hidden>Prof</option>
													<c:forEach items="${MEtudiant.getListEtud()}" var="etud">
														<option>${etud.getNomPr()}${prof.getPrenomPr()}</option>
													</c:forEach>
												</select> <span class="select-arrow"></span> --%>
											</div>
											<small class="form-text text-muted">ex. D123456
												</small>
										</div>

										<div class="form-group">
											<div class="input-group">
												<div class="input-group-addon">
													<span class="symbol-input100"> <i
														class="fa fa-sort-numeric-desc"></i>
												</div>
												<input class="form-control" type="number" name="NumS"
													placeholder="Numero de Semestre " required>
											</div>
											<small class="form-text text-muted">ex. 2</small>
										</div>
										<div class="form-group">
											<div class="input-group">
												<div class="input-group-addon">
													<span class="symbol-input100"> <i
														class="fa fa-sort-numeric-asc"></i>
												</div>
												<input class="form-control" type="date" name="Date"
													placeholder="Date d'inscription" required>
											</div>
											<small class="form-text text-muted">ex. 06/07/2020</small>
										</div>
										<div>
											<button type="submit" class="btn btn-success btn-sm">
												<i class="fa fa-dot-circle-o"></i> Submit
											</button>
											<button type="reset" class="btn btn-danger btn-sm">
												<i class="fa fa-ban"></i> Reset
											</button>
										</div>
									</div>
								</div>
							</div>


						</div>

					</div>
					<!-- .animated -->
				</div>
			</form>
			<!-- .content -->
		</div>


		<!-- /#right-panel -->

		<!-- Right Panel -->

		<script src="vendors/jquery/dist/jquery.min.js"></script>
		<script src="vendors/popper.js/dist/umd/popper.min.js"></script>
		<script src="vendors/bootstrap/dist/js/bootstrap.min.js"></script>
		<script src="assets/js/main.js"></script>


		<script src="vendors/chart.js/dist/Chart.bundle.min.js"></script>
		<script src="assets/js/dashboard.js"></script>
		<script src="assets/js/widgets.js"></script>
		<script src="vendors/jqvmap/dist/jquery.vmap.min.js"></script>
		<script src="vendors/jqvmap/examples/js/jquery.vmap.sampledata.js"></script>
		<script src="vendors/jqvmap/dist/maps/jquery.vmap.world.js"></script>
		<script>
				(function($) {
					"use strict";

					jQuery('#vmap').vectorMap({
						map : 'world_en',
						backgroundColor : null,
						color : '#ffffff',
						hoverOpacity : 0.7,
						selectedColor : '#1de9b6',
						enableZoom : true,
						showTooltip : true,
						values : sample_data,
						scaleColors : [ '#1de9b6', '#03a9f5' ],
						normalizeFunction : 'polynomial'
					});
				})(jQuery);
			</script>
</body>

</html>
