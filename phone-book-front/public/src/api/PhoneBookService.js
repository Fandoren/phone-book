export default class PhoneBookService {
    static async getAll() {
        const response = await fetch("http://localhost:10020/phoneBook")
        .then((response) => {
            if (!response.ok) {
              throw new Error("Bad response from server");
            }
            return response.json();
        })
        .catch((error) => {
            console.log(error);
            return null
        });
        return response;
    }

    static async getOne(id) {
        const response = await fetch("http://localhost:10020/phoneBook/" + id);
        return response.json();
    }

    static async save(entry) {
        const response = await fetch("http://localhost:10020/phoneBook", {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(entry)
        });
        return response
    }
    
    static async remove(id) {
        const response = await fetch("http://localhost:10020/phoneBook/" + id, {
            method: 'DELETE',
        });
        return response;
    }
    
    static async update(entry) {
        const response = await fetch("http://localhost:10020/phoneBook", {
            method: 'PUT',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(entry)
        });
        return response
    }

    static async actualize() {
        const response = await fetch("http://localhost:10020/phoneBook/actualize", {
            method: 'GET'
        });
        return response;
    }
}