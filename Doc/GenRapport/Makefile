prerequisites:
	@echo "Vérification du fonctionnement de pandoc"
	@which pandoc > /dev/null || (echo "❌ Impossible de trouver la commande pandoc." ; exit 1)
	@pandoc --version | grep -q "pandoc 2\.[0-9]\|pandoc 3" || (echo "❌ La version de pandoc doit être au moins 2.0." ; exit 1)
	@echo "Création du répertoire dist"
	@mkdir dist || (echo "Already exists")

prerequisites-pdflatex: prerequisites
	@echo "Vérification de la présence de pdflatex"
	@which pdflatex > /dev/null || (echo "❌ Impossible de trouver la commande pdflatex." ; exit 1)

clean:
	@echo "Suppression de Output.pdf"
	@rm dist/Output.pdf || (echo)
	@echo "Suppression de Output.html"
	@rm dist/Output.html || (echo)

pdf: prerequisites-pdflatex clean
	@echo "Génération de Output.pdf"
	@pandoc --defaults=config.yaml -o $(PWD)/../Rapport.pdf
