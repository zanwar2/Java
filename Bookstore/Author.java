
package bookstore;

public class Author implements Comparable<Author>
{
    protected String firstName;
    protected String lastName;
    protected String institution;
    
    //Constructor for author
    public Author(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    //Sets the institution of an author when called
    public void setInstitution(String institution)
    {
        this.institution=institution;
    }
    
    
    //Comparing method to sort authors by last names alphabetically
    @Override
    public int compareTo(Author other)
    {
        return this.lastName.compareTo(other.lastName);
    }
}
