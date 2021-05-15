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
<title>BachMek - Menu Etudiant</title>
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
				<a class="navbar-brand" href="HomeE.php"><img
					src="images/logo.png" alt="Logo"></a> <a
					class="navbar-brand hidden" href="HomeE.php"><img
					src="images/logo2.png" alt="Logo"></a>
			</div>

			<div id="main-menu" class="main-menu collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<div>
						<li class="active"><a href="HomeE.php"> <i
								class="menu-icon fa ti-home"></i>Home page
						</a></li>
						<li class="active"><a href="InscriptionE.php"> <i
								class="menu-icon fa fa-user"></i>Inscription
						</a></li>
					</div>

					<h3 class="menu-title">Consultations</h3>
					<!-- /.menu-title -->
					<div>
						<li class="active"><a href="RechercheEtudiant.php"> <i
								class="menu-icon fa  fa-info-circle"></i>Information Personnele
						</a></li>
						<li class="active"><a href="RechercheEtudiant.php"> <i
								class="menu-icon fa fa-check-square-o"></i>Vos Absences
						</a></li>
						<li class="active"><a href="GetFiliereSemestre.php"> <i
								class="menu-icon fa  fa-calendar"></i>Emploit du temps
						</a></li>
						<li class="active"><a href="RechercheEtudiant.php"> <i
								class="menu-icon fa  fa-spinner"></i>Vos Notes
						</a></li>
					</div>
					<h3 class="menu-title">Declaration</h3>
					<li class="active"><a href="declarerProbleme.php"> <i
							class="menu-icon fa  fa-comments-o"></i>Declarer un probleme
					</a></li>
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
						<h1>Consultation des Informations</h1>
					</div>
				</div>
			</div>
			<div class="col-sm-8">
				<div class="page-header float-right">
					<div class="page-title">
						<ol class="breadcrumb text-right">
							<li><a href="HomeE.php">Home page</a></li>
							<li class="active">Informations</li>
						</ol>
					</div>
				</div>
			</div>
		</div>
		<form >
			<div class="content mt-3">
				<div class="animated fadeIn">
					<div class="row">
						
						<div class="col-md-12">
							<div class="card">
								<div class="card-header">
									<strong class="card-title">Info etudiant</strong>
								</div>
								<br>
								<div class="col-sm-12">
									<div class="alert  alert-success alert-dismissible fade show"
										role="alert">
										<span class="badge badge-pill badge-success" style="margin-left: 28em;">Success</span>
										Ce Etudiant Existe .
										<button type="button" class="close" data-dismiss="alert"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
								</div>
								<div class="card-body">
									<div class="card-body card-block">
										<div class="form-group">
											<div class="form-group last" style="margin-left: 0em;">
												<h4 style="margin-left: 1em;">Photo</h4>
												<br>
												<div class="col-md-9">
													<div class="fileupload fileupload-new"
														data-provides="fileupload">
														<div class="fileupload-new thumbnail"
															style="width: 200px; height: 150px;">
															<img
																src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&text=no+image"
																alt="" ! />
														</div>
														<div
															class="fileupload-preview fileupload-exists thumbnail"
															style="max-width: 200px; max-height: 150px; line-height: 20px;"></div>

													</div>

												</div>
											</div>
										</div>
										<br>
										<div class="form-group">
											<label style="margin-left: 12em;">Nom</label> <label
												style="margin-left: 24em;">Prenom</label>
											<div class="input-group">
												<div class="input-group-addon">
													<i class="fa fa-tag"></i>
												</div>
												<input class="form-control" type="text"
													value="${MEtudiant.getEtudiant().getNom()}" disabled>
												<input class="form-control" type="text" name="Prenom"
													value="${MEtudiant.getEtudiant().getPrenom()}" disabled>
												<div class="input-group-addon">
													<i class="fa fa-tags"></i>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label style="margin-left: 12em;">CNI</label> <label
												style="margin-left: 25em;">CIN</label>
											<div class="input-group">
												<div class="input-group-addon">
													<i class="fa fa-asterisk"></i>
												</div>
												<input class="form-control" type="text"
													value="${MEtudiant.getEtudiant().getCni()}" disabled>
												<input class="form-control" type="text"
													value="${MEtudiant.getEtudiant().getCin()}" disabled>
												<div class="input-group-addon">
													<i class="fa fa-asterisk"></i>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label style="margin-left: 12em;">Email</label> <label
												style="margin-left: 24em;">Phone</label>
											<div class="input-group">
												<div class="input-group-addon">
													<i class="fa ti-email"></i>
												</div>
												<input class="form-control" type="email"
													value="${MEtudiant.getEtudiant().getEmail()}" disabled>
												<input class="form-control"
													value="${MEtudiant.getEtudiant().getPhone()}" disabled>
												<span class="symbol-input100">
													<div class="input-group-addon">
														<i class="fa fa-phone"></i>
													</div>
											</div>
										</div>

										<div class="form-group">
											<label style="margin-left: 12em;">Sexe</label> <label
												style="margin-left: 24em;">Filiere</label>
											<div class="input-group">
												<div class="input-group-addon">
													<i class="fa fa-female"></i>
												</div>
												<input class="form-control"
													value="${MEtudiant.getEtudiant().getSexe()}" disabled>
												<input class="form-control"
													value="${MEtudiant.getEtudiant().getFiliere()}" disabled>
												<span class="symbol-input100">
													<div class="input-group-addon">
														<i class="fa ti-view-list"></i>
													</div>
											</div>
										</div>

										<div class="form-group">
											<label style="margin-left: 12em;">Adresse</label> <label
												style="margin-left: 20.5em;">Ville de naissance</label>
											<div class="input-group">
												<div class="input-group-addon">
													<i class="fa ti-pin"></i>
												</div>
												<input class="form-control"
													value="${MEtudiant.getEtudiant().getAdresse()}" disabled>
												<input class="form-control" type="text"
													value="${MEtudiant.getEtudiant().getVilleN()}" disabled>
												<div class="input-group-addon">
													<i class="fa fa-map-marker"></i>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label style="margin-left: 10em;">Date de naissance</label> <label
												style="margin-left: 18em;">Annee Univ</label>
											<div class="input-group">
												<div class="input-group-addon">
													<i class="fa fa-calendar"></i>
												</div>
												<input class="form-control"
													value="${MEtudiant.getEtudiant().getDateN()}" disabled>
												<input class="form-control"
													value="${MEtudiant.getEtudiant().getAnneeUniv()}" disabled>
												<div class="input-group-addon">
													<i class="fa fa-calendar"></i>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label style="margin-left: 12em;">Country</label> <label
												style="margin-left: 22em;">Nombre d'Abs</label>
											<div class="input-group">
												<div class="input-group-addon">
													<i class="fa fa-map-marker"></i>
												</div>
												<input class="form-control"
													value="${MEtudiant.getEtudiant().getCountry()}" disabled>
												<input class="form-control"
													value="${MEtudiant.getEtudiant().getNbrAbs()}" disabled>
												<div class="input-group-addon">
													<i class="fa fa-check-square-o"></i>
												</div>
											</div>
										</div>
										<br>
										<div class="form-group">
											<div class="input-group">
												<label class="control-label col-md-3">File :</label> <span
													class="btn btn-theme02 btn-file"> <span
													class="fileupload-new"><i class="fa fa-paperclip"></i>
														File </span> <input type="file" class="inputstl" id="selphoto"
													name="file">
												</span> <span class="select-arrow"></span>
											</div>
										</div>
										<br>
										<div class="form-group">
											<a
												href="ModifierEtudiantE.php?CNI=${MEtudiant.getEtudiant().getCni()}"
												type="submit" class="btn btn-success btn-sm"> <i
												class="fa ti-marker-alt"></i> Modifier
											</a> <a
												href="supprimerEtudiantList.php?CNIE=${MEtudiant.getEtudiant().getCni()}"
												type="submit" class="btn btn-danger btn-sm"> <i
												class="fa ti-trash"></i> Supprimer
											</a>
										</div>
									</div>
								</div>
							</div>


						</div>
					</div>
					<!-- .animated -->
				</div>
				<div class="content mt-3">
					<div class="animated fadeIn">
						<div class="row">

							<div class="col-md-12">
								<div class="card">
									<div class="card-header">
										<strong class="card-title">Info parent</strong>
									</div>
									<div class="card-body">
										<div class="card-body card-block">
											<div class="form-group">
												<label style="margin-left: 25em;">Nom</label>
												<div class="input-group">
													<div class="input-group-addon">
														<i class="fa fa-tag"></i>
													</div>
													<input class="form-control"
														value="${Mparent.getParent().getNomP()}
											"
														disabled>
												</div>
											</div>
											<div class="form-group">
												<label style="margin-left: 25em;">Prenom</label>
												<div class="input-group">
													<div class="input-group-addon">
														<i class="fa fa-tags"></i>
													</div>
													<input class="form-control"
														value="${Mparent.getParent().getPrenomP()}
											"
														disabled>
												</div>
											</div>

											<div class="form-group">
												<label style="margin-left: 25em;">Phone</label>
												<div class="input-group">
													<div class="input-group-addon">
														<span class="symbol-input100"> <i
															class="fa fa-phone"></i>
													</div>
													<input class="form-control"
														value="${Mparent.getParent().getPhone()}" disabled>
												</div>
											</div>
											<div class="form-group">
												<label style="margin-left: 25em;">CNI</label>
												<div class="input-group">
													<div class="input-group-addon">
														<i class="fa fa-asterisk"></i>
													</div>
													<input class="form-control"
														value="${Mparent.getParent().getCniP()}" disabled>
												</div>
											</div>
											<div class="form-group">
												<label style="margin-left: 25em;">Email</label>
												<div class="input-group">
													<div class="input-group-addon">
														<i class="fa ti-email"></i>
													</div>
													<input class="form-control"
														value="${Mparent.getParent().getEmailP()}" disabled>
												</div>
											</div>
											<br>
											<div>
												<a
													href="ModifierParentE.php?CNIP=${Mparent.getParent().getCniP()}"
													type="submit" class="btn btn-success btn-sm"> <i
													class="fa ti-marker-alt"></i> Modifier
												</a> <a href="supprimerParentList.php?CNIP=${Mparent.getParent().getCniP()}" type="submit"
													class="btn btn-danger btn-sm"> <i class="fa ti-trash"></i>
													Supprimer
												</a>

											</div>
										</div>
									</div>
								</div>


							</div>
						</div>
						<!-- .animated -->
					</div>
					<!-- .content -->


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
                map: 'world_en',
                backgroundColor: null,
                color: '#ffffff',
                hoverOpacity: 0.7,
                selectedColor: '#1de9b6',
                enableZoom: true,
                showTooltip: true,
                values: sample_data,
                scaleColors: ['#1de9b6', '#03a9f5'],
                normalizeFunction: 'polynomial'
            });
        })(jQuery);
    </script>

</body>

</html>
