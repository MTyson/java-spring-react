import logo from './logo.svg';
import './App.css';
import React, { useState, useEffect } from 'react';

function App() {
  const ip = "34.132.155.86";
  const [todos, setTodos] = useState([]);

  // Fetch todos on component mount
  useEffect(() => {
    fetch('http://'+ip+':8080/todos')
      .then(response => response.json())
      .then(data => setTodos(data))
      .catch(error => console.error(error));
  }, []);

  // Function to add a new TODO item
  const addTodo = (description) => {
    fetch('http://'+ip+':8080/todos', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ description }),
    })
      .then(response => response.json())
      .then(newTodo => setTodos([...todos, newTodo]))
      .catch(error => console.error(error));
  };

  // Function to toggle completion of a TODO item
  const toggleTodoComplete = (id) => {
    const updatedTodos = todos.map(todo => {
      if (todo.id === id) {
        return { ...todo, completed: !todo.completed };
      }
      return todo;
    });
    setTodos(updatedTodos);

    // Update completion on the server (assuming an endpoint exists)
    fetch(`http://`+ip+`:8080/todos/${id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ completed: !todos.find(todo => todo.id === id).completed }),
    })
      .catch(error => console.error(error));
  };
  const deleteTodo = (id) => {
    const filteredTodos = todos.filter(todo => todo.id !== id);
    setTodos(filteredTodos);
    fetch(`http://`+ip+`:8080/todos/${id}`, {
      method: 'DELETE'
    })
    .catch(error => console.error(error));
  };

  return (
    <div className="App">
      <header className="App-header"><h1>My TODO App</h1></header>
        <input id="todo-input" type="text" placeholder="Add a TODO" onKeyDown={(e) => {
          if (e.key === 'Enter') {
            addTodo(e.target.value);
            e.target.value = '';
          }
        }} />
	  <button onClick={(e) => addTodo(document.getElementById('todo-input').value)}>Add TODO</button>
      <ul>
        {todos.map(todo => (
          <li key={todo.id}>
            <input type="checkbox" checked={todo.completed} onChange={() => toggleTodoComplete(todo.id)} />
            {todo.description}
	    <button onClick={() => deleteTodo(todo.id)}>&#x1F5D1;</button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;

