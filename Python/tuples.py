dog = ("Canis Familiaris", "dog", "carnivore", 12)
print dog

dog = dog + ("domestic",)
#result is...
#("Canis Familiaris", "Dog", "carnivore", 12, "domestic")
print dog

dog = dog[:3] + ("man's best friend",) + dog[3:]
#result is...
#("Canis Familiaris", "Dog", "carnivore", "man's best friend", "domestic")
print dog