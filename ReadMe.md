# FlowShop problem

Vous devez dans ce projet/TP implémenter les deux variantes du FlowShop problème suivante:

- Partie commune
    - Chaque machine ne peut exécuter qu'une tâche à la fois.
    - Chaque job ne peut avoir qu'une seule tâche exécutée à la fois.
    - Chaque tâche commencée sur une machine doit être terminée. 

- Variantes
    - Tâches non ordonnées : Les tâches associées aux différentes machines d'un job peuvent être effectuées dans n'importe quel ordre.
    - Tâches ordonnées : Pour chaque job la tâche effetuée sur la machine 0 doit être terminée avant d'effectuée la tâche sur la machine 1 et ainsi de suite.

Pour résoudre ces problèmes on utilisera *CpModel* de *OR-Tools*, un exemple basique d'utilisation de ce modèlé est donné dans *flowShop.Example.java*
Un représentation graphique des solutions est demandée, vous pourrez utiliser un canvas de javaFX pour cela.