Members:
Yunlin Zeng, Shengkai Yu, Lei Zheng

What we want to do

We are going to create a clean neural network in C++. 
Use network to recognize handwriting digits.
Provide Load/Save capability so once a neural network is trained it can be saved and later brought back. 

Background

The idea of using neural networks for the purpose of recognizing handwritten digits is not a new one. 
The inspiration for the architecture described here comes from articles written by two separate authors. 
The first is Dr. Yann LeCun, who was an independent discoverer of the basic backpropagation algorithm. Dr. LeCun hosts an excellent site
on his research into neural networks. 
In particular, you should view his "Learning and Visual Perception" section, which uses animated GIFs to show results of his research. 
The MNIST database (which provides the database of handwritten digits) was developed by him. 
I used two of his publications as primary source materials for much of my work, and I highly recommend reading his other publications 
too (they're posted at his site). 
Unlike many other publications on neural networks, Dr. LeCun's publications are not inordinately theoretical and math-intensive; rather,
they are extremely readable, and provide practical insights and explanations.

What are we doing now
Create relative classes
A neural network is composed of objects of four different classes: layers, neurons in the layers, connections from neurons in one layer 
to those in another layer, and weights that are applied to connections. 
Those four classes are reflected in the code, together with a fifth class -- the neural network itself -- which acts as a container for 
all other objects and which serves as the main interface with the outside world.

Forward Propagation
Forward propagation is the process whereby each of all of the neurons calculates its output value, based on inputs provided by the 
output values of the neurons that feed it.The process is initiated by calling NeuralNetwork::Calculate(). NeuralNetwork::Calculate() 
directly sets the values of neurons in the input layer, and then iterates through the remaining layers, calling each layer's NNLayer::Ca
lculate() function. This results in a forward propagation that's completely sequential, starting from neurons in the input layer and 
progressing through to the neurons in the output layer.

Back propagation
Back propagation is an iterative process that starts with the last layer and moves backwards through the layers until the first layer is
reached. Assume that for each layer, we know the error in the output of the layer. If we know the error of the output, then it is not
hard to calculate changes for the weights, so as to reduce that error. The problem is that we can only observe the error in the output
of the very last layer.

Build network
The code builds the illustrated neural network in stages, one stage for each layer. In each stage, an NNLayer is new'd and then added to
the NeuralNetwork's vector of layers. The needed number of NNNeurons and NNWeights are new'd and then added respectively to the layer's 
vector of neurons and vector of weights. Finally, for each neuron in the layer, NNConnections are added (using the NNNeuron::AddConnection()
function), passing in appropriate indices for weights and neurons.


Team division
Lei Zheng : find information, create classes and setup siamond 
Shengkai Yu : Forward Propagation and Back propagation
Yunlin Zeng: Build network and train the network


Github: 

